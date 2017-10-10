/*
* This is a sample file to create a consumer to consume a specific topic.
*/

import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerTest {
	
	public static void main(String[] args){
		String topic = "ProducerTest";
		String groupID = "ConsumerTest";
    
    		//the jaas is necessary to the app that requires Kerberos authentication
		System.setProperty("java.security.auth.login.config", "/usr/hdp/2.5.3.0-37/kafka/conf/kafka_jaas.conf");
		
		Properties props = new Properties();
    
    		//bootstrap.server is the addresses of kafka cluster
    		//port number is usually: 9092 while in Hortonworks it is 6667.
		props.put("bootstrap.servers", "xxxx:6667,xxxx:6667,xxxx:6667");
    
    		//usually protocol "PLAINTEXTSASL" is accompanied with Kerberos.
		props.put("security.protocol", "PLAINTEXTSASL");
    
    		//for group.id, the ACL needs to be set first for the client, or java has no access to the consumer group.
    		//command: $KafkaHome/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties xxxxx:2181 --add --allow-principal User:qingyangkong --operation All --group ConsumerTest
    		props.put("group.id", groupID);
    
    		//usually these properties keep same.
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
    		//create a consumer
    		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		
		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscripted to topc(s): " + topic);
		
		while(true){
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> record : records){
				System.out.println("offset = " + record.offset() + " key = " + record.key() + "value = " + record.value());
			}
		}
	}
}
