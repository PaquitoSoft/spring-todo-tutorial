package org.paquitosoft.tododemo.handlers;

import org.paquitosoft.tododemo.exceptions.EntityNotFoundException;
import org.paquitosoft.tododemo.exceptions.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {
		log.error(exception.getMessage());
		final HttpStatus notFound = HttpStatus.NOT_FOUND;
		final ErrorDto errorDto = ErrorDto.builder()
			.errorCode(exception.getErrorCode())
			.httpCode(notFound.value())
			.message(exception.getMessage())
			.build();

		return new ResponseEntity<ErrorDto>(errorDto, notFound);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
		log.error(exception.getMessage());
		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		final ErrorDto errorDto = ErrorDto.builder()
			.errorCode(exception.getErrorCode())
			.httpCode(badRequest.value())
			.message(exception.getMessage())
			.errors(exception.getErrors())
			.build();

		return new ResponseEntity<ErrorDto>(errorDto, badRequest);
	}
}
