package com.qaiware.messageservice.presentation.web.handler;

import org.springframework.http.ResponseEntity;

public interface MessageHandler {

    ResponseEntity<String> handleMessage(String type, String payLoad);
}
