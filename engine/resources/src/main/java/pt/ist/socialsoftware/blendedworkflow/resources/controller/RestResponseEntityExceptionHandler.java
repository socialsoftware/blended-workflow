package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = { BWException.class })
	protected ResponseEntity<Object> handleException(BWException bwe, WebRequest request) {
		logger.debug("handleBWException: {}, {}", bwe.getError().name(), bwe.getMessage());

		BWError error = new BWError(bwe.getError(), bwe.getMessage());
		return handleExceptionInternal(bwe, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
