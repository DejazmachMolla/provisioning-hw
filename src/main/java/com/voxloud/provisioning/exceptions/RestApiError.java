package com.voxloud.provisioning.exceptions;

import java.util.List;

import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voxloud.provisioning.util.JacksonUtils;

/**
 * This object is what gets sent to clients.
 */
public class RestApiError
{
	private final int httpStatus;
	private String userMessage;	
	private List<RestApiValidationError> validationErrors;

	@JsonCreator
	public RestApiError(@JsonProperty("httpStatus") RestApiHttpStatus httpStatus) {
		this.httpStatus = httpStatus.getStatusCode();
	}

	public static RestApiError fromHttpStatusCodeException(HttpStatusCodeException e) {
		String responseBody = e.getResponseBodyAsString();
		return JacksonUtils.fromJSON(responseBody, RestApiError.class);
	}

	@Override
	public String toString() {
		return JacksonUtils.toJSON(this);
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public RestApiError setUserMessage(String userMessage) {
		this.userMessage = userMessage;
		return this;
	}

	public List<RestApiValidationError> getValidationErrors() {
		return validationErrors;
	}

	public RestApiError setValidationErros(List<RestApiValidationError> restApiFieldErrors)	{
		this.validationErrors = restApiFieldErrors;
		return this;
	}
}