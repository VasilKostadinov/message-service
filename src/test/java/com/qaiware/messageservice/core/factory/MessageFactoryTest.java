package com.qaiware.messageservice.core.factory;

import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.message.EmotionMessage;
import com.qaiware.messageservice.core.message.TextMessage;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageFactoryTest {

    private MessageFactory messageFactory = new PropertiesConfigMessageFactory("message-mappings.properties");

    @Test
    public void shouldCreateTextMessage(){
        String payload = "text";
        AbstractMessage message = this.messageFactory.createMessage(TextMessage.SEND_TEXT,payload);
        assertTrue(message instanceof TextMessage);
        assertEquals(payload,message.getPayload());
    }

    @Test
    public void shouldCreateTextMessageWithArgs(){
        Integer id = 1;
        String payload = "text";
        LocalDateTime createdAt = LocalDateTime.now();

        AbstractMessage message = this.messageFactory.createMessage(id,TextMessage.SEND_TEXT,payload,createdAt);
        assertTrue(message instanceof TextMessage);
        assertEquals(payload,message.getPayload());
        assertEquals(id,message.getId());
        assertEquals(payload,message.getPayload());
        assertEquals(createdAt,message.getCreatedAt());
    }

    @Test
    public void shouldCreateEmotionMessage(){
        String payload = "emotion";
        AbstractMessage message = this.messageFactory.createMessage(EmotionMessage.SEND_EMOTION,payload);
        assertTrue(message instanceof EmotionMessage);
        assertEquals(payload,message.getPayload());
    }

    @Test
    public void shouldCreateEmotionMessageWithArgs(){
        Integer id = 1;
        String payload = "emotion";
        LocalDateTime createdAt = LocalDateTime.now();

        AbstractMessage message = this.messageFactory.createMessage(id,EmotionMessage.SEND_EMOTION,payload,createdAt);
        assertTrue(message instanceof EmotionMessage);
        assertEquals(payload,message.getPayload());
        assertEquals(id,message.getId());
        assertEquals(payload,message.getPayload());
        assertEquals(createdAt,message.getCreatedAt());
    }
}
