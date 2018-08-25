package com.qaiware.messageservice.presentation.web.handler;

import com.qaiware.messageservice.core.service.MessageService;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageHandlerTest {

    private static final String PAYLOAD = "test";
    private static final String TYPE = "test";

    private MessageHandler messageHandler;

    public MessageHandlerTest(){
        MessageService messageService = mock(MessageService.class);
        when(messageService.saveMessage(TYPE,PAYLOAD)).thenReturn(true);
        this.messageHandler = new MessageHandlerImpl(messageService);
    }

    @Test
    public void shouldReturn201EmptyResponseForValidMessage(){
        ResponseEntity<String> responseEntity = this.messageHandler.handleMessage(TYPE,PAYLOAD);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals("",responseEntity.getBody());
    }

    @Test
    public void shouldReturn412EmptyResponseForValidMessage(){
        ResponseEntity<String> responseEntity = this.messageHandler.handleMessage("","");
        assertEquals(HttpStatus.PRECONDITION_FAILED,responseEntity.getStatusCode());
        assertEquals("",responseEntity.getBody());
    }
}
