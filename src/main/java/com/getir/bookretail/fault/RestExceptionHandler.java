package com.getir.bookretail.fault;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author İkbal Arslan
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        log.warn("handleResourceNotFoundException: {} ", ex.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setError(ex.getMessage());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<Object> handleResourceAlreadyExist(ResourceAlreadyExistException ex) {
        log.warn("handleResourceAlreadyExistException: {} ", ex.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setError(ex.getMessage());
        customErrorResponse.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotRegisteredException.class)
    protected ResponseEntity<Object> handleResourceNotRegistered(ResourceNotRegisteredException ex) {
        log.warn("handleResourceNotRegisteredException: {} ", ex.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setError(ex.getMessage());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughResourcesException.class)
    protected ResponseEntity<Object> handleNotEnoughResources(NotEnoughResourcesException ex) {
        log.warn("handleNotEnoughResourcesException: {} ", ex.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setError(ex.getMessage());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("handleMethodArgumentNotValidException: {}", ex.getMessage());
        final Errors errors = ex.getBindingResult();
        final ValidationErrorRestResponse response = new ValidationErrorRestResponse();

        if (errors.hasFieldErrors()) {
            errors.getFieldErrors()
                    .forEach(fieldError -> {
                        final String description = fieldError.getDefaultMessage();
                        response.addFieldError(fieldError.getField(), description);
                    });
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
