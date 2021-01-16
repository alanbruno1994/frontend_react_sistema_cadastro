package com.registrationsystem.workshop.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.registrationsystem.workshop.services.exceptions.CompositeException;
import com.registrationsystem.workshop.services.exceptions.DuplicateElement;
import com.registrationsystem.workshop.services.exceptions.ObjectNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFound.class)
	public ResponseEntity<StandardError> resourceNotFound(ObjectNotFound e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), "Recurso não encontrado:", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DuplicateElement.class)
	public ResponseEntity<StandardError> resourceNotFound(DuplicateElement e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(), status.value(), "Erro causado devido:", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(CompositeException.class)
	public ResponseEntity<StandardError> resourceNotFound(CompositeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(), status.value(), "Erro causado devido:", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidToken.class)
	public ResponseEntity<StandardError> resourceNotFound(InvalidToken e, HttpServletRequest request) {		
		HttpStatus status = HttpStatus.LOCKED;
		StandardError err = new StandardError(Instant.now(), status.value(), "Erro causado devido:", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<StandardError> resourceNotFound(MissingRequestHeaderException e, HttpServletRequest request) {	
		HttpStatus status = HttpStatus.LOCKED;
		StandardError err = new StandardError(Instant.now(), status.value(), "Erro causado devido:", "Não foi passado o token!", request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> resourceNotFound(Exception e, HttpServletRequest request) {		
		HttpStatus status = HttpStatus.LOCKED;
		StandardError err = new StandardError(Instant.now(), status.value(), "Erro causado devido:", "Não foi passado o token!", request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
