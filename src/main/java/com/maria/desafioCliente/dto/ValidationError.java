package com.maria.desafioCliente.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

	public ValidationError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	private List<FieldMessage> errors = new ArrayList<>();
	
	public void addError(String fieldName, String fieldMessage) {
		errors.add(new FieldMessage(fieldName, fieldMessage));
	}
}
