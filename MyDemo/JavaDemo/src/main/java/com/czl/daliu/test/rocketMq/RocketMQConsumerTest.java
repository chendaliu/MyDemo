package com.czl.daliu.test.rocketMq;


public class RocketMQConsumerTest {
	public static void main(String[] args) {

		String mqNameServer = "192.168.35.129:9876";
		String mqTopics = "MQ-MSG-TOPICS";

		String consumerMqGroupName = "CONSUMER-MQ-GROUP";
		RocketMQListener mqListener = new RocketMQListener();
		RocketMQConsumer mqConsumer = new RocketMQConsumer(mqListener,
				mqNameServer, consumerMqGroupName, mqTopics);
		mqConsumer.init();

		try {
			Thread.sleep(1000 * 60L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
