package com.cts.kafkademo.model;

import java.io.Serializable;

public class PersistenceEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int recordId;
	private String operation;
	private String status;
	
	public PersistenceEvent() {}

	public PersistenceEvent(int recordId, String operation, String status) {
		super();
		this.recordId = recordId;
		this.operation = operation;
		this.status = status;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PersistenceEvent [recordId=" + recordId + ", operation=" + operation + ", status=" + status + "]";
	}
	
	
}
