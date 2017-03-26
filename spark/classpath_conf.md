# class path configuration in Spark application
`spark-submit` is always used to submit a Spark application. In cluster where spark is installed, there are one master node and one or more worker nodes and tasks are sent from the master to workers. So it is important to make sure all dependencies can be reached by every node. Although configuration options can be found in the [official website](http://spark.apache.org/docs/latest/configuration.html), it is necessary to clarify the ambiguties of these jars.  
* Pass through command
* Pass through `addJar` and `addFile`
* Difference between client and cluster mode
* How to use System.setProperty(key, value)

### 1. Pass through commands
`spark.driver.extraClassPath` is to set libs that is going to be used by driver node.  
`spark.executor.extraClassPath` is to set libs that is going to be used by executors.  

These are how to use them in commands:  
`--conf "spark.driver.extraClassPath={path}"`  
`--conf "spark.executor.extraClassPath={path}"`  
For `spark.driver.extraClassPath`, there is an alias `--driver-class-path` that can be used as when submit spark app through command `spark-submit`. If there are multiple files added, make sure they are separated by colon. 

`--jars {path}` is to distribute jar files across nodes and add them to driver and executor classpath.  
Neither driver extra classpath nor executor extra classpath is able to add jars from driver node to executors. In real spark job, it is common that third-party jar files are required in tasks. If the spark job is run in a cluster, jars need to be reachable by nodes containing executors. In this scenario, `--jars` can be used to distribut jar files into nodes in cluster and add them into driver and executor classpath.  

`--files {path}` is similar as `--jars {path}`, and it is to distribute arbitrary file in spark cluster.  
The way to use files passed by `--files {path}` is shown below:  
Submit spark job in this way: `spark-submit --class path/to/fileName --files path/tofile job.jar`  

```
val filePath = SparkFile.get("fileName")
val result = doSthWithFile(filePath)

def doSthWithFile(path: String):Int = {
  ....
}
```
method `SparkFile()` cannot be used before SparkContext setup, or NullPointer exception would be raised.

### 2. Pass through `addJar` and `addFile`   
Methods `SparkContext.addJar` and `SparkContext.addFile` do the thing pretty much like option `--jars` and `--files`. There are 2 differences:  
First is that `addJar` won't add jar files into driver's classpath. What `addJar` does is to distribute jar files across multiple worker nodes and driver node class path does not change.
See example below:  
Create a simple class and package it into a jar file
```
package utils;

public class Calculator {
	
	public int addOne(int i){
		return i + 1;
	}
	public int addTwo(int i){
		return i + 2;
	}
}
```
Then use class Calculator in Spark job
```
object TestDependencies {
  def main(args:Array[String]): Unit = {
    val sparkConf = new SparkConf()
    val sc = new SparkContext(sparkConf)

    sc.addJar("file:/absolute/path/to/utils.jar")
        
    val data = 1 to 10 toList
    val rdd = sc.makeRDD(data)
    
    val rdd_1 = rdd.map ( x => {
      val myCalculator = new Calculator
      myCalculator.addOne(x)
    })
    
    rdd_1.collect().foreach { x => print(x + "||") }
  }
}
```
Run the spark job in yarn-client mode by command `spark-submit --class TestDependencies --verbose --master yarn --deploy-mode client sparkTest-0.0.1-SNAPSHOT.jar`, we can get the correct answer: `2||3||4||5||6||7||8||9||10||11||`  

But if I attempt to use the class in driver node, it won't work, see example below:
```
object TestDependencies {
  def main(args:Array[String]): Unit = {
    val sparkConf = new SparkConf()
    val sc = new SparkContext(sparkConf)

    sc.addJar("file:/absolute/path/to/utils.jar")
    
    val l = List(1, 2, 3, 4, 5)
    val l_1 = l.map { x => {
        val myCalculator = new Calculator
        myCalculator.addOne(x)
      }
    }
    println(l_1)
    
    val data = 1 to 10 toList
    val rdd = sc.makeRDD(data)
    
    val rdd_1 = rdd.map ( x => {
      val myCalculator = new Calculator
      myCalculator.addOne(x)
    })
    
    rdd_1.collect().foreach { x => print(x + "||") }
  }
}
```
When I try class Calculator in driver mode rather RDD that sits in executors, I get the error "Exception in thread "main" java.lang.NoClassDefFoundError: utils/Calculator". That is because the jar file is not added into driver's classpath. Through test above it can be certified that `addJar` only add jars into executors' classpath rather than driver's.  

There is another thing to be noticed is if spark job is submitted in local mode, spark will use driver classpath even if for manipulatiosn in RDD that should saved in executors because there is only one node in local mode. If `addJar` is used in the local mode, jar files cannot be found as they are added into executors' class path.  

The second difference is `--jars` and `--file` will distribute jars and files from client node rather than drive node, while `addJar` and `addFile` distribute resources from driver node.  
What is client node? There must be a main method in a spark job, the node where main method run is called driver node. The node where the spark job is submitted us called client node.   
Usually the driver and client node is the same node, but in yarn cluster mode, they are not the same. In this mode, `addJar()` cannot find the resource because it is running on driver node but resource is usually saved in client node. To solve this problem, add jars in driver node or use `--jars` to distribute resources from client node.

### 3. Difference between client and cluster mode
For client mode, driver program is run in the node where you submit the Spark job, while driver program will be run in the any node that resource manager found in the cluster for cluster mode. This is the biggest difference between client mode and cluster mode.  
In client mode, result can be seen in teh console so spark job is easier to debug. Client mode also gives developer full control where the driver node is running.  
In clusetr mode, it is easier to allocate resources and always used for production.  

### 4. How to use System.setProperty()
System.setProperty is used when some parameters need to be submitted at the time JVM is created in the host. For exmaple, if any service is using Kerberos to authenticate, when spark attempts to use it, the option `java.security.auth.login.config` needs to be used.  
It should be used like `System.setProperty("java.security.auth.login.config", "/path/to/jaas/file")`. One ting to attention is that `System.setProperty` is executed before files get distributed, so the method cannot use the file that is added by `--files` or `sparkContext.addFile({path})`.
