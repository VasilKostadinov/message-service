package com.qaiware.messageservice.presentation.web.handler;

import com.qaiware.messageservice.core.factory.MessageFactory;
import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.repository.MessageRepository;
import com.qaiware.messageservice.core.validator.MessageValidator;
import com.qaiware.messageservice.core.validator.ValidationChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageHandlerImpl implements MessageHandler {

    private static final String EMPTY_STRING = "";

    private MessageValidator messageValidator;
    private MessageRepository messageRepository;
    private MessageFactory messageFactory;

    public MessageHandlerImpl(MessageValidator messageValidator, MessageRepository messageRepository, MessageFactory messageFactory){
        this.messageValidator = messageValidator;
        this.messageRepository = messageRepository;
        this.messageFactory = messageFactory;
    }

    @Override
    public ResponseEntity<String> handleMessage(String type, String payLoad) {
        AbstractMessage message = this.messageFactory.createMessage(type,payLoad);
        ValidationChain validationChain = this.messageValidator.validateMessage(message);
        if (validationChain.isValid()){
            messageRepository.save(message);
            return new ResponseEntity<String>(EMPTY_STRING,HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(EMPTY_STRING,HttpStatus.PRECONDITION_FAILED);
    }
}
