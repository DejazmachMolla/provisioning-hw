package com.voxloud.provisioning.exceptions;


public class NotFoundException extends RestApiException {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super(RestApiHttpStatus.NOT_FOUND);
	}
	public NotFoundException(String message) {
		super(RestApiHttpStatus.NOT_FOUND, message);
	}
}