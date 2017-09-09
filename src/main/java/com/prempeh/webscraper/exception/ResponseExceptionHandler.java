package com.prempeh.webscraper.exception;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prempeh.webscraper.model.GenericResponse;


@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messages;

	public ResponseExceptionHandler() {
		super();
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException ex,
			final WebRequest request) {
		logger.error("404 Status Code", ex);
		final GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.resourceNotFoundException", null, request.getLocale()), ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ UnknownHostException.class })
	public ResponseEntity<Object> handleUnknownHostException(final UnknownHostException ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		final GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.unknownHostException", null, request.getLocale()), ex.getMessage());
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ IOException.class })
	public ResponseEntity<Object> handleIOException(final IOException ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		final GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.iOException", null, request.getLocale()), ex.getMessage());
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex,
			final WebRequest request) {
		logger.error("500 Status Code", ex);
		final GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.IllegalArgumentException", null, request.getLocale()), ex.getMessage());
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleInternalException(final Exception ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		final GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.exception", null, request.getLocale()), ex.getMessage());
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		final GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.exception", null, request.getLocale()), ex.getMessage());
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
