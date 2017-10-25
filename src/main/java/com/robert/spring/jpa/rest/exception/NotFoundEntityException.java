package com.robert.spring.jpa.rest.exception;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 24/10/17
 **/
public class NotFoundEntityException extends Exception {

    public NotFoundEntityException(String message) {
        super(message);
    }

}