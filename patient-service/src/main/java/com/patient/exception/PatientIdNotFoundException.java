package com.patient.exception;

public class PatientIdNotFoundException extends RuntimeException {
	
	public PatientIdNotFoundException() {
	super();
	}
	
	
	public PatientIdNotFoundException(String message) {
		super(message);
	}
	

}


