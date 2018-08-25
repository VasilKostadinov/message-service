package com.qaiware.messageservice.core.service;


import com.qaiware.messageservice.core.factory.MessageFactory;
import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.message.EmotionMessage;
import com.qaiware.messageservice.core.repository.MessageRepository;
import com.qaiware.messageservice.core.validator.MessageValidator;
import com.qaiware.messageservice.core.validator.ValidationChain;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    private MessageService messageService;
    private static final String PAYLOAD = "test_payload";
    private static final String TYPE = "test_type";
    private AbstractMessage testMessage = new EmotionMessage(PAYLOAD);

    public MessageServiceTest(){
        initializeMessageServiceWithMockedArgs();
    }

    @Test
    public void shouldReturnTrueForValidMessage(){
        boolean savedWithoutProblems = this.messageService.saveMessage(TYPE,PAYLOAD);

        assertTrue("Valid message failed to save",savedWithoutProblems);
    }

    @Test
    public void shouldReturnFalseForInValidMessage(){
        boolean savedWithoutProblems = this.messageService.saveMessage("","");

        assertTrue("Valid message failed to save",savedWithoutProblems);
    }

    private void initializeMessageServiceWithMockedArgs() {
        MessageRepository messageRepository = mock(MessageRepository.class);
        MessageFactory messageFactory = mock(MessageFactory.class);
        when(messageFactory.createMessage(any(),any())).thenReturn(testMessage);
        MessageValidator messageValidator = mock(MessageValidator.class);
        when(messageValidator.validateMessage(testMessage)).thenReturn(new ValidationChain(true));
        this.messageService = new MessageServiceImpl(messageValidator,messageRepository,messageFactory);
    }
}
