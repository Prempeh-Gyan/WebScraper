package com.prempeh.webscraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
	/**
	 * The "message" variable represent the message the JVM sends when an error or
	 * exception occurs The "error" variable represent the error that has occurred
	 */
	private String message;
	private String error;

}
