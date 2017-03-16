# RDD, partitions and nodes
content
<li>What is RDD, parition and node?</li>
<li>Which one should use? map or mapPartitions?</li>

### 1. What is RDD, parition and node?
#### definitions
RDD(Resilient Distributed Dataset) is an abstraction of distributed data. Although RDD looks as same as other collection, it consists of multiple partitions that are saved across multiple nodes.  

For a RDD, there are multiple partitions, these partitions would be scattered across multiple nodes.  
Single node can handle multiple partitions(with optimum 2-4 partitions per CPU).  
One partition can only reside on the a single node.  

[Check this stackoverflow website for more details](http://stackoverflow.com/questions/354909/is-there-a-difference-between-foreach-and-map)  
   
We can see the partitions of a RDD by following codes. `rdd.partitions` returns array of partitions within RDD. 
To make RDD, 2 methods `makeRDD` and `parallelize` are equal, see _Tip1_.
```
val sparkConf = new SparkConf()
val sc = new SparkContext(sparkConf)

val rdd_1 = sc.makeRDD(1 to 30 toList)
println("number of rdd_1 partitions is: " + rdd_1.partitions.size)

val rdd_2 = sc.makeRDD(1 to 100 toList)
println("number of rdd_2 partitions is: " + rdd_2.partitions.size)

val rdd_3 = sc.makeRDD(1 to 10 toList, 10)
println("number of rdd_3 partitions is: " + rdd_3.partitions.size)
```
results of the codes are:  
```
number of rdd_1 partitions is: 16
number of rdd_2 partitions is: 16
number of rdd_3 partitions is: 10
```
Through results, it is relatively obvious to see the default of number of partitions in currently is 16 and it is also possible to set a specific number of parttitions by adding second parameter in method `makeRDD` and `parallelize`.
#### How partition works in Spark?
All logics that is to be executed in worker node needs to be pushed into a closure, serialized and passed across multiple nodes. 
In every node, every partition holds a separate memory for the closure. That means if I pass a variable to node, every partition in the node have a copy of the variable that **DOES NOT** sync with each other.
Program below can certify what stated above:  

```
val rdd_1 = sc.makeRDD(1 to 10, 2)
var counter = 0
rdd_1.map(x => {counter += 1; (x, counter)}).collect.foreach{case(x, count) => print(s"($x, $count) ")}
```
result: (1, 1) (2, 2) (3, 3) (4, 4) (5, 5) (6, 1) (7, 2) (8, 3) (9, 4) (10, 5)  

```
val rdd_2 = sc.makeRDD(1 to 10, 10)
var counter = 0
rdd_2.map(x => {counter += 1; (x, counter)}).collect.foreach{case(x, count) => print(s"($x, $count) ")}
```
result: (1, 1) (2, 1) (3, 1) (4, 1) (5, 1) (6, 1) (7, 1) (8, 1) (9, 1) (10, 1)  

In resutls, after calculation, counter ranges from 1 to 5 in rdd_1, and only 1 in rdd_2. In rdd_1 10 elements is separated into 2 partitions so counter is incremented for 5 times in every single partition, while 10 elements are devided into 10 partitions and counter is incremented onnce in the partition. From results it is not difficult to know every partition has a "heap" to do all operations that passed by master for elements within it.  
More partitions, more space used and more tasks spark is able execute in the same time period. 

### 2. Which one should use? map or mapPartitions?
There are 2 most common methods used in Spark: map and mapPartitions. Basically these 2 methods are just different ways handling RDD: first is against every single element in a RDD and second is against all RDD elements in a partition and treat it as an iterator. Difference between `foreach` and `foreachPartition` is in the same way. (Check _tip2_ to see difference between `foreach` and `map`)  

If you are sick of my bad English, see the codes below:  
```
val sparkConf = new SparkConf()
val sc = new SparkContext(sparkConf)
val rdd_3 = sc.makeRDD(1 to 10 toList, 10)

//functions applied in map is to process elements in RDD one by one
val rdd_4 = rdd_3.map { x => x + 1 }
//fucntinos applied in mapPartitions is to process partitions of elements as an iterator
val rdd_5 = rdd_3.mapPartitions(ite => {ite.map { x => x + 1 }}) 

rdd_4.collect.foreach(x => print(x + " "))
rdd_5.collect.foreach(x => print(x + " "))
```
Results printed from rdd_4 and rdd_5 are exactly same. In fact, for most of cases, there are not difference between these 2 ways processing RDDs.  

So why we need to use `mapPartitions` and `foreachPartition`?  
If there are any operations where heavy objects are required to instantiate, it is better to add this logics into partition rather than apply it directly on RDD, because applying into partition only require to create one object in a partition while `map` will creaet as many objects as number of elements in RDD within the partition.  
Let's see an example:
I have a rdd that contains a lot of elements, these elements are held in the different nodes each of elements in the RDD needs to be inserted into a database.  
In this scenario, if I create a new connection between data and database for every single elements in RDD, there would be millions of connections instantiated if there are a large scale of data in there, and obviously it is not always a good way doing this. So it maybe better if I create only one connectino for a partition, and all elements of RDD in this partition would share the same one.  
```
  stream.foreachRDD(rdd => {
        val jsonDF = sqlContext.read.json(rdd)
        val formattedDF = input.InputUtil.generateNewDFwithCols(jsonDF, fieldsName)
        formattedDF.foreachPartition { ite =>
          {
            val conf = utility.HBaseUtil.getHBaseConfiguration
            val tableToPush = utility.HBaseUtil.getHTable(conf, tableName)
            ite.foreach{
            	val putOnce = new Put(rowKey.getBytes)
            	putOnce.addColumn("columnFamily".getBytes, "qualifierName".getBytes, qualifierValue.getBytes)
            	tableToPush.put(putOnce)
            }
          }
        }
  }
 ```
#### Tip 1
**Difference between map and foreach:**  
Map is a transformation and will not result in execution of logics within it until when action is summoned. In contrast, Operations in foreach will be executed immediately.  
So by convention, foreach is used when side-effect is expected while map is used when side effect is not required. Actually map can also do operations with side-effect.  
Edited stuff from [this stackoverflow website](http://stackoverflow.com/questions/354909/is-there-a-difference-between-foreach-and-map)

#### Tip 2
**Difference between makeRDD and parallelize:**  
there are 2 ways convert a collection to a RDD
eg: 
```
val list = 1 to 10 toList
val rdd_1 = sc.makeRDD(list)
val rdd_2 = sc.parallelize(list)
```
These 2 methods are basically the same things, and makeRDD is identical to parallelize according to [this link](http://stackoverflow.com/questions/31428128/in-spark-api-what-is-the-difference-between-makerdd-functions-and-parallelize-f)
