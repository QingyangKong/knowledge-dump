# class path configuration in Spark application
`spark-submit` is always used to submit a Spark application. In cluster where spark is installed, there are one master node and one or more worker nodes and tasks are sent from the master to workers. So it is important to make sure all dependencies can be reached by every node. Although configuration options can be found in the [official website](http://spark.apache.org/docs/latest/configuration.html), it is necessary to clarify the ambiguties of these jars.  
* Pass through command
* Pass through `addJar` and `addFile`
* Difference between client and cluster mode

### 1. Pass through commands
`spark.driver.extraClassPath` is to set libs that is going to be used by driver node.  
`spark.executor.extraClassPath` is to set libs that is going to be used by executors.  
These are how to use them in commands:  
`--conf "spark.driver.extraClassPath={path}"`  
`--conf "spark.executor.extraClassPath={path}"`  
For `spark.driver.extraClassPath`, there is an alias `--driver-class-path` that can be used as when submit spark app through command `spark-submit`. 

If the app is running under multiple nodes, these 2 options, in most of cases, should be the same make sure all dependencies can be found by each node in cluster.  
`--jars` will send jars in a temporary directory that is accessible for all nodes.  
`--files` so the same thing as `--jars` and the difference is `--file` is used for arbitrary file.  
Whatever `--jars` and `--files` will not add jars or conf files into class path, so they need to be added into cp explicitly if necessary.  
See this example:

### 2. Pass through `addJar` and `addFile`   
`sc.addJar` does the same thing as `--jars` and `sc.addFile` does the same thing as `--files`. One add dependencies and files in codes while the other one adds depdencies and files in command. The only difference is that priority, passing though codes has higher priority than command.  
Example to use `sc.addFile`
```
sc.addFile("path/to/file.jaas")
val absolutePath = SparkFiles.get("file.jaas")
System.setProperty("java.security.auth.login.config", absolutePath)
```
Add jaas file for spark app in all nodes. First save it in a temporary dir and then `absolutePath` can be reached by every node in the cluster.
### 3. Difference between client and cluster mode
