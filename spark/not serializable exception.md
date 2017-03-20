# Serilization problem
agenda
* What is serilization problem and what causes it?
* Ways preventing the problem.

### 1. What is serialization problem and how it comes?
Because all manipulations against on the RDD (or other distributed datatype like dataframe and dataset) in Spark need to be encapsulated into a closure, serialized and then pass across multiple nodes. If there is anything, like a object without extending `Serializable`, that cannot be serialized, the problem comes. 

When serlization problems come, Spark will shoot the exception as `Exception in thread "main" org.apache.spark.SparkException: Task not serializable: at org.apache.spark.util.ClosureCleaner$.ensureSerializable`. If the exception shows up, it measn logics to pass across nodes has something cannot be serlized and the logics failed to be executed on the distributed dataset. ClosureCleaner is the class to test if anything in the closure is able to be serialized.
 
Let's one examples:
First define a class that contains methods:
```
object TestSerialization {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf
    val sc = new SparkContext(sparkConf)
    
    val data = 1 to 5 toList
    val rdd = sc.makeRDD(data)
    
    val myCalculator = new calculator(1)
    
    rdd map myCalculator.calculate 
  }
}

class calculator(num : Int){
  def calculate(input: Int): Int = num + input
}
```

Package it and use spark-submit --class TestSerialization sparkTest-0.0.1-SNAPSHOT.jar to run that. Then the exception information is:
```
Exception in thread "main" org.apache.spark.SparkException: Task not serializable
        at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:304)
        at org.apache.spark.util.ClosureCleaner$.org$apache$spark$util$ClosureCleaner$$clean(ClosureCleaner.scala:294)
        at org.apache.spark.util.ClosureCleaner$.clean(ClosureCleaner.scala:122)
        at org.apache.spark.SparkContext.clean(SparkContext.scala:2079)
        ...
Caused by: java.io.NotSerializableException: calculator
Serialization stack:
        - object not serializable (class: calculator, value: calculator@748f93bb)
        - field (class: TestSerialization$$anonfun$main$1, name: myCalculator$1, type: class calculator)
        - object (class TestSerialization$$anonfun$main$1, <function1>)
        at org.apache.spark.serializer.SerializationDebugger$.improveException(SerializationDebugger.scala:40)
        at org.apache.spark.serializer.JavaSerializationStream.writeObject(JavaSerializer.scala:47)
        at org.apache.spark.serializer.JavaSerializerInstance.serialize(JavaSerializer.scala:101)
        at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:301)
        ... 20 more
```
The reason exception raised is the object `myCalculator` is instantiated in master node, and it cannot be passed across nodes. The class calculator does not extend from interface `Serializable` so its instantiation cannot be serialized.  

So it seems that we are not allowed to use unserializable object in worker node. The answer is NO. It is allowed to instantiate unserializable classes in any worker node and use any method defined in the class while it is not allowed to ship any of this unserializable instantiation across any nodes.  
See the example below:
```
object TestSerialization {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf
    val sc = new SparkContext(sparkConf)
    
    val data = List("Andy", "Bob", "Clair")
    val rdd = sc.makeRDD(data)
    
    rdd map{ x => (x, new person(x)) } count 
  }
}

class person(name : String){
  def printInfo(): Unit = println("This is " + name)
}
```
Program above can be run successfully because it only counts instances without shipping them.
```
object TestSerialization {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf
    val sc = new SparkContext(sparkConf)
    
    val data = List("Andy", "Bob", "Clair")
    val rdd = sc.makeRDD(data)
    
    rdd map{ x => (x, new person(x)) } collect
  }
}

class person(name : String){
  def printInfo(): Unit = println("This is " + name)
}
```
This program causes serializtion problem because method `collect` will collect all instances to master node and all records in a RDD are required to be transferred in this process.

In short, any transport of unserializable object will cause unserializable problem.

### 2. Ways preventing Exception task unserializable
