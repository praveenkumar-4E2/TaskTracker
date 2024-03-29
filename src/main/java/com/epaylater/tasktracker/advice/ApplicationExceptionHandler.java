package com.epaylater.tasktracker.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epaylater.tasktracker.exception.TaskIdExistAlreadyException;
import com.epaylater.tasktracker.exception.TaskNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex)
	{
		Map<String,String> errorMap=new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TaskNotFoundException.class)
	public Map<String,String> HandleTaskNotFoundException(TaskNotFoundException ex)
	{
		Map<String,String> errorMap=new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TaskIdExistAlreadyException.class)
	public Map<String,String> HandleTaskIdExistAlreadyException(TaskIdExistAlreadyException ex)
	{
		Map<String,String> errorMap=new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String,String> HandleHttpRepquest(HttpRequestMethodNotSupportedException ex)
	{
		Map<String,String> errorMap=new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String,String> HandleHttpMessageNotReadableException(HttpMessageNotReadableException ex)
	{
		Map<String,String> errorMap=new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
		
	}
	
	
	
	
}
