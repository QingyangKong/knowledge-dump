# Kafka Principals
### What is Kafka used for?
Suppose you have an application that needs to read messages from a Kafka topic, apply some logics on them. There must be a processer(servlet, service or consumer) manipulate the data and this can work well for a while.  

But if the rate at which incoming data exceeds the rate at which the application can process? For example, the app receives 10 messages per second but is only able to process 1 message per second. In this scenario, the application may fall farther and farther behind, unable to keep up with the rate of incoming messages.  

Kafka, as a message queue, serves as a role to scale up the comsuption of messages by store and process them in paralle across multiple nodes. It has a multiple partitions and consumers for a topic to receive and consume data in a cluster.  

For use cases, it is common for Kafka consumers to do high latency operations such as write to a database or to HDFS, or a time-consuming computation on the data.

### How messages distributed in the kafka topic partitions?
When a producer published a message to the topic, it would assign a partition ID for that message. A partition ID can be either set in the method or hashed by the message's key. If the message doesn't have a key or partition ID is not set, kafka will add the message in round robin way.  
After the partition ID set, server only append the message to log file for that specific partition only.  
In this way, Kafka provides ordering guarantees only within a partition.

### How messages consumed by consumers in a consumer group?
Each consumer in a consumer group reads only from its assigned partitions.  
If number of consumers is fewer than partitions, a consumer can be attached to multiple partitions. If the number of consumers is more than partitions, some consumers will stay idle.
