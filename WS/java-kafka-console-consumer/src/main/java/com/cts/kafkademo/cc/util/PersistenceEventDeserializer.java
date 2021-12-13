package com.cts.kafkademo.cc.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.kafka.common.serialization.Deserializer;

import com.cts.kafkademo.model.PersistenceEvent;

public class PersistenceEventDeserializer implements Deserializer<PersistenceEvent> {

	@Override
	public PersistenceEvent deserialize(String topic, byte[] data) {
		PersistenceEvent pe=null;
		
		try(ByteArrayInputStream bis = new ByteArrayInputStream(data);
				ObjectInputStream ois = new ObjectInputStream(bis)){
			
			pe=(PersistenceEvent) ois.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return pe;
	}

}
