package com.daliu.test.rocketMq;

import com.alibaba.rocketmq.common.message.Message;

public class RocketMQProducerTest {
	public static void main(String[] args) {

		String mqNameServer = "192.168.35.129:9876";
		String mqTopics = "MQ-MSG-TOPICS";

		String producerMqGroupName = "PRODUCER-MQ-GROUP";
		RocketMQProducer mqProducer = new RocketMQProducer(mqNameServer,
				producerMqGroupName, mqTopics);
		mqProducer.init();

		for (int i = 0; i < 5; i++) {

			Message message = new Message();
			message.setBody(("I send message to RocketMQ " + i).getBytes());
			mqProducer.send(message);
		}

	}
}
