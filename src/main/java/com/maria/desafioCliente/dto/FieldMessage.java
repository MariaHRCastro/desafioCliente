package com.maria.desafioCliente.dto;

public class FieldMessage {

	private String fieldName;
	private String fieldMessage;
	
	public FieldMessage(String fieldName, String fieldMessage) {
		super();
		this.fieldName = fieldName;
		this.fieldMessage = fieldMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return fieldMessage;
	}
	
	
	
}
