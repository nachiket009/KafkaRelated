package com.home;

import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.Callback;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.ErrorLoggingCallback;
import org.apache.kafka.common.record.Record;
import org.apache.kafka.common.serialization.StringSerializer;

//Create java class named “SimpleProducer”
public class KafkaProducer1 {

	public static void main(String[] args) throws Exception {

		String topicName = "test3";
		String hostPort = "“localhost:9092";
		String acksValue = "all";
		int retries = 0;
		int batchSize = 16384;
		long bufferMemory = 33554432;
		String keySerializerValue = StringSerializer.class.getName();
		String valueSerializerValue = StringSerializer.class.getName();

		Properties props = new Properties();
		props.put("bootstrap.servers", hostPort);
		props.put("acks", acksValue);
		props.put("retries", retries);
		props.put("batch.size", batchSize);
		props.put("linger.ms", 1);
		props.put("buffer.memory", bufferMemory);
		props.put("key.serializer", keySerializerValue);
		props.put("value.serializer", valueSerializerValue);
		int noOfMsgs = 100000;
		long sleepTime=2000;

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		
		for (int i = 0; i < noOfMsgs; i++) {

			ProducerRecord<String, String> pr = new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)+" As Message");
			//Callback cb = new ErrorLoggingCallback(); 
			producer.send(pr);
			System.out.print("Key : " + pr.key() + " , ");
			System.out.println("Value : " + pr.value());
			
			Thread.sleep(sleepTime);
		}
		producer.close();
		
	}
}