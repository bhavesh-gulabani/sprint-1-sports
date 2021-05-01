package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Custom exceptions are meant for business rule violation

import com.cg.util.ErrorDetail;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> resourceExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
	 
	@ResponseBody
	@ExceptionHandler(IncorrectPriceException.class)
	ResponseEntity<?> IncorrectPriceException(IncorrectPriceException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(EmptyInventoryException.class)
	ResponseEntity<?> EmptyInventoryException(EmptyInventoryException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(UserAlreadyExistsException.class)		
	ResponseEntity<?> exceptionHandler(UserAlreadyExistsException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(WrongCredentialsException.class)		
	ResponseEntity<?> signInExceptionHandler(WrongCredentialsException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}	
	
	@ResponseBody
	@ExceptionHandler(NotEnoughStockException.class)		
	ResponseEntity<?> lessStockExceptionHandler(NotEnoughStockException ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = ex.getFieldError().toString().split(";")[0];
		ErrorDetail errorDetail = new ErrorDetail(new Date(), errorMessage, request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
