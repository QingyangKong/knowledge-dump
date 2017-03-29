# Kafka Principals
### What is Kafka used for?
Suppose there is an application reading messages from a Kafka topic, and applying some logics on them. Data keeps coming to the app and the app keeps processing it.  

But if the rate at which data comes exceeds the rate the application can process? For example, the app receives 10 messages per second but is only able to process 1 message per second. In this scenario, the application may fall farther and farther behind, unable to keep up with incoming messages and even worse thing is that some messages may be missed because not processed in time. 

Kafka, as a message queue, serves as a role to scale up the comsuption of messages by store and process them in paralle across multiple nodes. It has a multiple partitions and consumers for a topic to receive and consume data in a cluster.  

Normal use cases for Kafka is to do high latency operations such as I/O to a database or HDFS, and a time-consuming computation(like complicated computation) on the data.

### How messages distributed in the kafka topic partitions?
When a producer published a message to the topic, it would assign a partition ID for that message. A partition ID can be either set in the method or hashed by the message's key. If the message doesn't have a key or partition ID is not set, kafka will add the message in round robin way.  
After the partition ID set, server only append the message to that specific partition only and in this way, Kafka provides ordering guarantees only within a partition.  A kafka cluster may have multiple nodes and on node may have multiple partition and by this way, parallel computing is possible.

### How messages consumed by consumers in a consumer group?
Each consumer in a consumer group reads only from its assigned partitions.  
If number of consumers is fewer than partitions, a consumer can be attached to multiple partitions. If the number of consumers is more than partitions, some consumers will stay idle.
