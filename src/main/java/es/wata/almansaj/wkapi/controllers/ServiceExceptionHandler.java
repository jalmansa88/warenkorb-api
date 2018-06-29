package es.wata.almansaj.wkapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.wata.almansaj.wkapi.exceptions.ElementNotFoundInDBException;
import es.wata.almansaj.wkapi.response.ApiResponse;

@ControllerAdvice
public class ServiceExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiResponse> genericHandler(Exception e) {

		LOG.error("Generic Exception caught in ExceptionHandler.", e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unknown Server Error"));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ApiResponse> genericHandler(HttpMessageNotReadableException e) {
		
		LOG.error("HttpMessageNotReadableException caught in ExceptionHandler.", e);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
	}

	@ExceptionHandler(ElementNotFoundInDBException.class)
	protected ResponseEntity<ApiResponse> serviceExceptionHandler(ElementNotFoundInDBException e) {

		LOG.error("ElementNotFoundInDBException caught in ExceptionHandler, with message: " + e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	}
	
}
