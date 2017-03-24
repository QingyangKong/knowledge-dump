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
### 3. Difference between client and cluster mode
### 4. How to use System.setProperty()
