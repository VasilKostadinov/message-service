package com.qaiware.messageservice.dal.exception;

public class DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable throwable){
        super(message,throwable);
    }
}
