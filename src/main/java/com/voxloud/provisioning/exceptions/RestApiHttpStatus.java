package com.voxloud.provisioning.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Keeping the Http statuses as simple as possible
 */
public enum RestApiHttpStatus
{
	/**
	 * When everything went well.
	 */
	OK(200, "Ok"),

	/**
	 * When there is a problem on the server that is not caused by the client.
	 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

	/**
	 * When something is wrong in the input received from the client.
	 */
	BAD_REQUEST(400, "Bad Request"),

	/**
	 * When the client is not logged in.
	 */

	UNAUTHORIZED(401, "Unauthorized"),

	/**
	 * When the client does not have permission to carry out an operation.
	 */
	FORBIDDEN(403, "Forbidden"),

	/**
	 * When resource for the request URL is not found.
	 */
	NOT_FOUND(404, "Not Found"),
	
	/**
	 * When trying to save a record that violates uniqueness constraints.
	 */
	CONFLICT(409, "Conflict");


	private final int statusCode;
	private final String description;

	private RestApiHttpStatus(int statusCode, String description) {
		this.statusCode = statusCode;
		this.description = description;
	}

	@JsonCreator
	public static RestApiHttpStatus parse(@JsonProperty("statusCode") int statusCode) {
		switch(statusCode) {
		case 200: return OK;
		case 500: return INTERNAL_SERVER_ERROR;
		case 400: return BAD_REQUEST;
		case 401: return UNAUTHORIZED;
		case 403: return FORBIDDEN;
		case 404: return NOT_FOUND;
		case 409: return CONFLICT;
		default: throw new IllegalArgumentException("Invlaid RestApiStatus Code " + statusCode);
		}
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getDescription() {
		return description;
	}
}
