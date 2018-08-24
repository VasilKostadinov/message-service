package com.qaiware.messageservice.core.service;

import com.qaiware.messageservice.core.factory.MessageFactory;
import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.repository.MessageRepository;
import com.qaiware.messageservice.core.validator.MessageValidator;
import com.qaiware.messageservice.core.validator.ValidationChain;

public class MessageServiceImpl implements MessageService {

    private MessageValidator messageValidator;
    private MessageRepository messageRepository;
    private MessageFactory messageFactory;

    public MessageServiceImpl(MessageValidator messageValidator, MessageRepository messageRepository, MessageFactory messageFactory){
        this.messageValidator = messageValidator;
        this.messageRepository = messageRepository;
        this.messageFactory = messageFactory;
    }

    @Override
    public boolean saveMessage(String type, String payload) {
        AbstractMessage message = this.messageFactory.createMessage(type,payload);
        ValidationChain validationChain = this.messageValidator.validateMessage(message);
        if (validationChain.isValid()){
            messageRepository.save(message);
            return true;
        }
        return false;
    }
}
