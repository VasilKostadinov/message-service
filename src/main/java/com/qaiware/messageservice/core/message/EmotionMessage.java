package com.qaiware.messageservice.core.message;

import java.time.LocalDateTime;

public class EmotionMessage extends AbstractMessage {

    public static final String SEND_EMOTION = "send_emotion";

    public EmotionMessage(String payload) {
        super(SEND_EMOTION, payload);
    }

    public EmotionMessage(Integer id, String payload, LocalDateTime createdAt) {
        super(id,SEND_EMOTION,payload,createdAt);
    }
}
