# class path configuration in Spark application
`spark-submit` is always used to submit a Spark application. In cluster where spark is installed, there are one master node and one or more worker nodes and tasks are sent from the master to workers. So it is important to make sure all dependencies can be reached by every node. Although configuration options can be found in the [official website](http://spark.apache.org/docs/latest/configuration.html), it is necessary to clarify the ambiguties of these jars.  
* Pass through command
* Pass through `addJar` and `addFile`
* Difference between client and cluster mode

### 1. Pass through commands
`spark.driver.extraClassPath` is to set libs that is going to be used by driver node.  
`spark.executor.extraClassPath` is to set libs that is going to be used by executors.  
These are how to use them in commands:  
`--conf "spark.driver.extraClassPath"={path}`  
`--conf "spark.executor.extraClassPath"={path}`  
For `spark.driver.extraClassPath`, there is an alias `--driver-class-path` that can be used as when submit spark app through command `spark-submit`. 

If the app is running under multiple nodes, these 2 options, in most of cases, should be the same make sure all dependencies can be found by each node in cluster.
`--jars` will send jar files after the option to multiple nodes and these jar files will not be included into driver ot worker classpath. They still need to be explictly set in the command option.

See this example:

### 2. Pass through `addJar`
`sc.addJar` does the same thing as `--jars`. First one is add dependencies in codes while the second one add depdencies in command. The only difference is that priority, passing though codes has higher priority than command.  

### 3. Difference between client and cluster mode
