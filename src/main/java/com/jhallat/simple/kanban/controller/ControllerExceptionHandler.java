package com.jhallat.simple.kanban.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jhallat.simple.kanban.exception.ErrorMessage;
import com.jhallat.simple.kanban.exception.FieldErrorMessage;
import com.jhallat.simple.kanban.exception.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	ErrorMessage exceptionHandler(ValidationException e) {
		return new ErrorMessage("400", e.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<FieldErrorMessage> fieldErrorMessages = 
				fieldErrors.stream().map(fieldError -> 
					new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		return fieldErrorMessages;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	ErrorMessage exceptionHandler(NotFoundException e) {
		return new ErrorMessage("404", e.getMessage());
	}
	
}
