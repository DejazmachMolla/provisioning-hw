package com.voxloud.provisioning.exceptions;

import java.util.Arrays;
import java.util.List;

public class RestApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final RestApiError restApiError;
	
	public RestApiException(RestApiError restApiError) {
		this.restApiError = restApiError;
	}
	
	public RestApiException(RestApiHttpStatus httpStatus) {
		this.restApiError = new RestApiError(httpStatus);
	}
	
	public RestApiException(RestApiHttpStatus httpStatus, String message) {
		this.restApiError = new RestApiError(httpStatus);
		this.restApiError.setUserMessage(message);
	}
	
	public RestApiException userMessage(String userMessage)	{
		this.restApiError.setUserMessage(userMessage);
		return this; 
	}
	
	public RestApiException validationErrors(List<RestApiValidationError> validationErrors) {
		this.restApiError.setValidationErros(validationErrors);
		return this;
	}
	
	public RestApiException validationErrors(RestApiValidationError... validationErrors) {
		this.restApiError.setValidationErros(Arrays.asList(validationErrors));
		return this;
	}

	public RestApiError getRestApiError() {		
		return restApiError ;
	}
}