package org.develdeco.hexagonal.micro_simple.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {

    public ApiException(String message, HttpStatus status) {
        super(status, message);
    }

    public ApiException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}

