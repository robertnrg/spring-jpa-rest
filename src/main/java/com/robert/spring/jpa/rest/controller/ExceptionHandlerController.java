package com.robert.spring.jpa.rest.controller;

import com.robert.spring.jpa.rest.dto.ErrorDetailDTO;
import com.robert.spring.jpa.rest.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorDetailDTO> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error("{}", ex.getMessage());

        return new ResponseEntity<>(new ErrorDetailDTO(ex.getClass().getSimpleName(), HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({NotFoundEntityException.class})
    public ResponseEntity<ErrorDetailDTO> handleNotFoundEntityException(NotFoundEntityException ex) {
        LOGGER.error("{}", ex.getMessage());

        return new ResponseEntity<>(new ErrorDetailDTO(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class, IOException.class, Exception.class})
    public ResponseEntity<ErrorDetailDTO> handleRuntimeException(final Exception ex) {
        LOGGER.error("{}", ex.getMessage());

        return new ResponseEntity<>(new ErrorDetailDTO(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}