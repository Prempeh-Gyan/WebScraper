package com.prempeh.webscraper.model;

import lombok.Data;

/**
 * This class is used as a model for representing all errors and exceptions in
 * the application. This model is used together with the
 * RestResponseEntityExceptionHandler to display error messages to clients
 * 
 * @author Prince Prempeh Gyan
 * @version 1.0 <br/>
 *          Date: 09/09/2017
 *
 */
@Data
public class GenericResponse {
	/**
	 * The "message" variable represent the message the JVM sends when an error or
	 * exception occurs The "error" variable represent the error that has occurred
	 */
	private String message;
	private String error;

	public GenericResponse(String message2, String string) {
		this.message = message2;
		this.error = string;
	}

}
