/*
 * This is a sample to create a producer to push messages into 3 partitions whitin a topic
 * 
 * */

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

//metadata.broker.list is not used anymore
//bootstrap.servers is new one to replace it
//zookeeper.connect is not required

public class ProducerTest {
	public static void main(String[] args) throws Exception {

		System.setProperty("java.security.auth.login.config", "/usr/hdp/2.5.3.0-37/kafka/conf/kafka_jaas.conf");
		
		String topicName = "ProducerTest";
		Properties props = new Properties();
		props.put("bootstrap.servers", "xxxx:6667,xxxx:6667,xxxx:6667");
		props.put("security.protocol", "PLAINTEXTSASL");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.ms", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		System.out.println("properties are set successfully");
		
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		System.out.println("kafka producer created successfully");
		
		for (int i = 0; i < 12; i++){
    
			//param1 topicName
			//param2 partition number 
			//param3 key to be sent
			//param4 value to be sent
			ProducerRecord<String, String> pr = null;
			if( i % 3 == 0){
				pr = new ProducerRecord<String, String>(topicName, 0, 0 + "", "partition_1: " + i);
			}
			if( i % 3 == 1){
				pr = new ProducerRecord<String, String>(topicName, 1, 1 + "", "partition_2: " + i);
			}
			if( i % 3 == 2){
				pr = new ProducerRecord<String, String>(topicName, 2, 2 + "", "partition_3: " + i);
			}
			System.out.println("a new producer record is created!");
			producer.send(pr);
		}
		
		System.out.println("Message sent successfully");
		producer.close();
	}
}
