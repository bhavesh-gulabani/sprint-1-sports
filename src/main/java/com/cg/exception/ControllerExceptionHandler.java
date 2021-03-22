package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

// Custom exceptions are meant for business rule violation

import com.cg.util.ErrorDetail;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> resourceExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
}
