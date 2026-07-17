package com.hdfclife.policyproposal.exception;

import org.springframework.http.HttpStatus;

public class BusinessValidationException extends RuntimeException {

    private final HttpStatus status;

    public BusinessValidationException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public BusinessValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
