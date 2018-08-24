package com.qaiware.messageservice.core.message;

import java.time.LocalDateTime;

public class TextMessage extends AbstractMessage {

    public static final String SEND_TEXT = "send_text";

    public TextMessage(String payload) {
        super(SEND_TEXT, payload);
    }

    public TextMessage(Integer id, String payload, LocalDateTime createdAt) {
        super(id,SEND_TEXT,payload,createdAt);
    }
}
