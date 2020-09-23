package br.com.myproject.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings("serial")
public class RestControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handlerException(HttpServletRequest request, EntityNotFoundException exception) {
		 Map<String, Object> responseBody = new HashMap<>() {{
			 put("title", "Entity not found");
			 put("status", HttpStatus.NOT_FOUND.value());
			 put("path", request.getRequestURI());
			 put("message", exception.getMessage());
		 }};
		
		return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
	}

}
