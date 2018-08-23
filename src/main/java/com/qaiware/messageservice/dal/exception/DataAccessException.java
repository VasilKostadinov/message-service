package com.qaiware.messageservice.dal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.SERVICE_UNAVAILABLE, reason="Cannot access database.")
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable throwable){
        super(message,throwable);
    }
}
