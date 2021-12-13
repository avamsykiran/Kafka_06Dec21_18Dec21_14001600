package com.cts.kafkademo.cc.app;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.cts.kafkademo.cc.util.PersistenceEventDeserializer;
import com.cts.kafkademo.model.PersistenceEvent;

public class ConsoleConsumerWithCustomMessage {

	public static void main(String[] args) {
		// Config the consumer properties
		Properties consumerProps = new Properties();
		consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PersistenceEventDeserializer.class.getName());
		consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "myJavaApp");

		// Create the kafka consumer;

		KafkaConsumer<String, PersistenceEvent> kafkaConsumer = new KafkaConsumer<String, PersistenceEvent>(consumerProps);

		kafkaConsumer.subscribe(Arrays.asList("TopicC"));

		while (true) {
			ConsumerRecords<String, PersistenceEvent> msgs = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, PersistenceEvent> msg : msgs) {
				System.out.println(msg.topic() + "\t" + msg.key() + "\t" + msg.value());
			}
		}

	}

}
