package com.home;

import java.util.Properties;

class ConsumerThread extends Thread {
	private Properties props;
	private String topicName;
	private int batchSize;

	public ConsumerThread(Properties props, String topicName, int batchSize) {
		this.props = props;
		this.topicName = topicName;
		this.batchSize = batchSize;
	}

	public void run() {
		String threadName = Thread.currentThread().getName();
		KafkaConsumer1.runConsumer(props, topicName, batchSize, threadName);

	}

}
