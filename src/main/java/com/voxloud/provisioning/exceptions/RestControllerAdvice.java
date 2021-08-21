package com.voxloud.provisioning.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/*
 * Exceptions thrown from classes annotated with @RestController
 * will be handled using the appropriate mehtod in the class
 * annotated with @ControllerAdvice
 */
@ControllerAdvice(annotations=RestController.class)
public class RestControllerAdvice {
	private static Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);
	protected final static String JSON = "application/json";

	protected ResponseEntity<RestApiError> createResponseEntity(RestApiError restApiError) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpStatus responseStatus = HttpStatus.valueOf(restApiError.getHttpStatus());
		ResponseEntity<RestApiError> result = new ResponseEntity<>(restApiError, headers, responseStatus);
		return result;
	}

	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<RestApiError> handleRestApiException(RestApiException e) {
		logger.error("Api Error caused by exception", e);
		return this.createResponseEntity(e.getRestApiError());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestApiError> handleException(Exception e) {
		logger.error("Api Error caused by exception", e);
		RestApiError restApiError = new RestApiError(RestApiHttpStatus.INTERNAL_SERVER_ERROR);
		restApiError.setUserMessage("The server encountered an error and could not complete the request");
		
		return createResponseEntity(restApiError);
	}
}
