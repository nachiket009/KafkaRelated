package com.home;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumer1 {

	public static void main(String[] args) throws Exception {
		String topicName = "test3";
		String hostPort = "“localhost:9092";
		String groupId = "GroupA";
		int batchSize = 5;
		String autoCommitValue = "true";
		String keySerializerValue = StringDeserializer.class.getName();
		String valueSerializerValue = StringDeserializer.class.getName();
		String autoCommitInterval = "1000";
		String sessionTimeOut = "30000";

		Properties props = new Properties();
		props.put("bootstrap.servers", hostPort);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", autoCommitValue);
		props.put("auto.commit.interval.ms", autoCommitInterval);
		props.put("session.timeout.ms", sessionTimeOut);
		props.put("key.deserializer", keySerializerValue);
		props.put("value.deserializer", valueSerializerValue);

		runConsumer(props, topicName, batchSize, null);
		
	}
	
	public static void runConsumer(Properties props, String topicName, int batchSize, String threadName) {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList(topicName));

		while (true) {
			try {

				ConsumerRecords<String, String> records = consumer.poll(batchSize);
				for (ConsumerRecord<String, String> record : records) {
					System.out.print("threadName : " + threadName + " , ");
					System.out.print("Key : " + record.key() + " , ");
					System.out.print("Value : " + record.value() + " , ");
					System.out.print("Offset : " + record.offset() + " , ");
					System.out.print("parition : " + record.partition() + " , ");					
					System.out.println("consumer.assignment : " + consumer.assignment() + " || ");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				consumer.commitSync();
				// consumer.close();
			}
		}
	}

}
