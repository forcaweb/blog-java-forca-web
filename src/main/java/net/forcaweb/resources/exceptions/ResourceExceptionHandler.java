package net.forcaweb.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import net.forcaweb.exceptions.DBExceptions;
import net.forcaweb.exceptions.ResourceErrorInternalServerException;
import net.forcaweb.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> requestNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Recurso não encontrado.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI(), error);
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DBExceptions.class)
	public ResponseEntity<StandardError> database(DBExceptions e, HttpServletRequest request){
		String error = "Solicitação Proibida.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI(), error);
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceErrorInternalServerException.class)
	public ResponseEntity<StandardError> erroServer(ResourceErrorInternalServerException e, HttpServletRequest request){
		String error = "Algum problema foi encontrado no servidor. Não se preocupe, um de nossos especialistas vai corrigir!";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI(), error);
		return ResponseEntity.status(status).body(err);
	}
}
