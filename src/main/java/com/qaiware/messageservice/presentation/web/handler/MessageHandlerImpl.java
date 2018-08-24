package com.qaiware.messageservice.presentation.web.handler;


import com.qaiware.messageservice.core.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageHandlerImpl implements MessageHandler {

    private static final String EMPTY_STRING = "";

    private MessageService messageService;

    public MessageHandlerImpl(MessageService messageService){
        this.messageService = messageService;
    }

    @Override
    public ResponseEntity<String> handleMessage(String type, String payLoad) {
        boolean savedWithoutErrors = this.messageService.saveMessage(type,payLoad);
        if (savedWithoutErrors){
            return ResponseEntity.status(HttpStatus.CREATED).body(EMPTY_STRING);
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(EMPTY_STRING);
    }
}
