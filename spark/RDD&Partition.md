<h1>RDD, partitions and nodes</h1>
content
<li>What is RDD, parition and node?</li>
<li>Which one we should use? map or mapPartitions?</li>

<h3>1. What is RDD, parition and node?</h3>
RDD(Resilient Distributed Dataset) is an abstraction of distributed data. Although it looks as same as other collection, it consists of multiple partitions that are saved across multiple nodes.  

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
number of rss_1 partitions is: 16
number of rdd_2 partitions is: 16
number of rdd_3 partitions is: 10
```
Through results, it is relatively obvious to see the default of number of partitions in current spark is 16 and it is also possible to set a specific number of parttitions.

<h3>2. Which one we should use? map or mapPartitions?</h3>
We can usually see methods: map and mapPartitions, and basically these 2 methods are different ways handling RDD: first is against every single element in a RDD and second is against partition and treat it as a iterator. Difference between `foreach` and `foreachPartition` is in the same way. (Check _tip2_ to see difference between `foreach` and `map`)  

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
If there is any manipulations where heavy object is required to instantiate, it is better add this logics into partition rather than apply it directly on RDD.
Let's take an example:
I have a rdd that contains a lot of elements, these elements are held in the different nodes each of elements in the RDD needs to be inserted into a database.  
In this scenario, if I create a new connection between data and database for every single elements in RDD, there would be millions of connections instantiated, and obviously it is not always a good way doing this. So it maybe better if I create only one connectino for a partition, because number of partitions of RDD can be set by ourselves.  
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
<h4>Tip 1</h4>
<b>Difference between map and foreach:</b>  
Map is a transformation and will not result in execution of logics within it until when action is summoned. In contrast, Operations in foreach will be executed immediately.  
So by convention, foreach is used when side-effect is expected while map is used when side effect is not required. Actually map can also do operations with side-effect.  
Edited stuff from [this stackoverflow website](http://stackoverflow.com/questions/354909/is-there-a-difference-between-foreach-and-map)

<h4>Tip 2</h4>
<b>Difference between makeRDD and parallelize:</b>  
there are 2 ways convert a collection to a RDD
eg: 
```
val list = 1 to 10 toList
val rdd_1 = sc.makeRDD(list)
val rdd_2 = sc.parallelize(list)
```
These 2 methods are basically the same things, and makeRDD is identical to parallelize according to [this link](http://stackoverflow.com/questions/31428128/in-spark-api-what-is-the-difference-between-makerdd-functions-and-parallelize-f)
