package com.reception.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ReceptionExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<String> exception(PatitentDetailsNotFoundException billingId) {
		//log.error("ResourceNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(billingId.getMessage(), HttpStatus.NOT_FOUND);
	}

}
