package utility
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.StreamingContext

//sample file that spark streaming receives messages from a Kafka topic.
//because kafka provide a streaming data as a data source, kafka is usually used with spark streaming.

object FetchDStreamFromKafka {
  def fetchDataFromTopic(topicName: String, ssc: StreamingContext): DStream[String] = {
    //input topic name expected to receive
    
    //topic name and partition number within this topic make up object TopicAndParition
    //attention if one parition wihtin the topics is missing, messages in the parition cannot be received
    //if the parititon is more then existed one in the topic, there would be an error.
    val topicAndPartition: Map[kafka.common.TopicAndPartition, Long] = 
      Map(
          kafka.common.TopicAndPartition(topicName, 0) -> 0L,
          kafka.common.TopicAndPartition(topicName, 1) -> 0L,
          kafka.common.TopicAndPartition(topicName, 2) -> 0L
          )
    
    //configurations set for zookeeper and Kafka
    val kafkaParams = Map[String, String](
      "metadata.broker.list" -> "xxxx:6667,xxxx:6667,xxxx:6667",
      "zookeeper.connect" -> "xxxx:2181",
      "zookeeper.connection.timeout.ms" -> "10000",
      "zookeeper.session.timeout.ms" -> "10000",
      "security.protocol" -> "PLAINTEXTSASL"
    )
    
    
    //get InputDstream from Kafka queues  
    val messages = KafkaUtils.
    createDirectStream[String, 
      String, 
      StringDecoder, 
      StringDecoder, 
      (String, String, Long)](
        ssc,
        property.ClusterConf.kafkaParams,
        topicAndPartition,
        (msg: kafka.message.MessageAndMetadata[String, String]) => {(msg.topic, msg.message(), msg.offset)}
      )
      
     //return DStream with offset
     messages.map(InputNorm.addOffset)
  }
}
