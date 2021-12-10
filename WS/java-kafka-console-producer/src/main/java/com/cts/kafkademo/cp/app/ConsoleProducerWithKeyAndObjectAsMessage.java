package com.cts.kafkademo.cp.app;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.cts.kafkademo.cp.model.PersistenceEvent;
import com.cts.kafkademo.cp.util.PersistenceEventSerializer;

public class ConsoleProducerWithKeyAndObjectAsMessage {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		//Config the producer properties
		Properties producerProps = new Properties();
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PersistenceEventSerializer.class.getName());
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		
		//Create the kafka producer;
		KafkaProducer<String,PersistenceEvent> kafkaProducer = new KafkaProducer<String, PersistenceEvent>(producerProps);
		
		boolean shallContinue=true;
		
		String topic = "TopicC";
		
		while(shallContinue) {
			PersistenceEvent pe = new PersistenceEvent();
			System.out.print("key: ");
			String key = scan.nextLine();
			System.out.print("RecordId: ");
			pe.setRecordId(scan.nextInt());
			System.out.print("Operation: ");
			pe.setOperation(scan.next());
			System.out.print("Status: ");
			pe.setStatus(scan.next());
			
			ProducerRecord<String,PersistenceEvent> msg = new ProducerRecord<String, PersistenceEvent>(topic,key,pe);
			kafkaProducer.send(msg);
			kafkaProducer.flush();
			
			System.out.print("Continue(yes/no): ");
			shallContinue = "yes".equals(scan.next());
		}
		
		kafkaProducer.close();
		scan.close();

	}

}
