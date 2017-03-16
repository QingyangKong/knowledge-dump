<h1>Use of accumulator in Spark</h1>
content
<li>What accumulator is used for?</li>

<h3>What is accumulator is used for?</h3>
Spark, as a distributed framework, save data across multiple nodes in the cluster and the data that saved in this way is called RDD. When any operations are going to be executed on RDD, logics would be passed into multiple nodes at first place.  
In this secenario, there would be a problem that variables cannot be synced in the logics as I talked in this [article](https://github.com/QingyangKong/knowledge-dump/blob/master/spark/RDD%26Partition.md).  
Let's take an exmaple from official API:

```
 scala> val accum = sc.accumulator(0)
 accum: spark.Accumulator[Int] = 0

 scala> sc.parallelize(Array(1, 2, 3, 4)).foreach(x => accum += x)
 ...
 10/09/29 18:41:08 INFO SparkContext: Tasks finished in 0.317106 s

 scala> accum.value
 res2: Int = 10
 ```
 
 By using this, it is easy to create counter in distributed system. In the project, it is easy to calculate how many records in that meet some requirement.  
 Other points of accumulator:  
 Values of accumulator cannot be accessed in the worker nodes.
