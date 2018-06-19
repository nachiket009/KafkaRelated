package com.home;

import java.util.Properties;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaThreadedConsumer {

	public static void main(String[] args) throws Exception {
		String topicName = "test3";
		String hostPort = "“localhost:9092";
		String groupId = "GroupA";
		int batchSize = 10;
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
		
		int threadsToSpwan = 10;
		
		ConsumerThread[] consumerThreads = new ConsumerThread[threadsToSpwan];
		for(int i=0 ; i < threadsToSpwan ; i++){
			consumerThreads[i] = new ConsumerThread(props, topicName, batchSize);
		}
		for(int i=0 ; i < threadsToSpwan ; i++){
			consumerThreads[i].start();
		}
				
		//runConsumer(props, topicName, batchSize, null);

	}

	

	

}
