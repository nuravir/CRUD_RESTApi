package com.pb.demo.exceptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		log.error("[resourceNotFoundException] Exception raised:{}",ex.getMessage());
		List <String> DefaultMessage = Arrays.asList(ex.getMessage());
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				DefaultMessage,
				request.getDescription(false));
		return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,WebRequest request){
		
		log.error("[handleMethodArgumentNotValid] Exception raised:{})",ex.getMessage());
		List <String> DefaultMessage = ex.getBindingResult()
		        .getFieldErrors()
		        .stream()
		        .map(DefaultMessageSourceResolvable::getDefaultMessage)
		        .collect(Collectors.toList());
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				DefaultMessage,
				request.getDescription(false));
		//ResourceNotValidException resourceNotValidException = new ResourceNotValidException(message);
		return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

