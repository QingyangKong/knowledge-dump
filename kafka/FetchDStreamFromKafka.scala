package utility
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.StreamingContext

object FetchDStreamFromKafka {
  def fetchDataFromTopic(topicName: String, ssc: StreamingContext): DStream[String] = {
    //input topic name expected to receive
    val topicAndPartition: Map[kafka.common.TopicAndPartition, Long] = 
      Map(
          kafka.common.TopicAndPartition(topicName, 0) -> 0L,
          kafka.common.TopicAndPartition(topicName, 1) -> 0L,
          kafka.common.TopicAndPartition(topicName, 2) -> 0L
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