package com.cts.kafkademo.cp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.kafka.common.serialization.Serializer;

import com.cts.kafkademo.cp.model.PersistenceEvent;

public class PersistenceEventSerializer implements Serializer<PersistenceEvent> {

	@Override
	public byte[] serialize(String topic, PersistenceEvent data) {
		byte[] result = null;

		if (data != null) {
			/*
			 * StringBuffer sb = new StringBuffer(); sb.append("{");
			 * sb.append("'recordId':"+data.getRecordId()+",");
			 * sb.append("'operation':'"+data.getOperation()+"',");
			 * sb.append("'status':'"+data.getStatus()+"'}");
			 * 
			 * result = sb.toString().getBytes();
			 */

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ObjectOutputStream out = new ObjectOutputStream(bos);) {
				
				out.writeObject(data);
				out.flush();
				
				result = bos.toByteArray();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
