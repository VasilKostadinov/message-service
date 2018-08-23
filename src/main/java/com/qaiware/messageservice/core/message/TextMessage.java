package com.qaiware.messageservice.core.message;

public class TextMessage extends AbstractMessage {

    public static final String SEND_TEXT = "send_text";

    public TextMessage(String payload) {
        super(SEND_TEXT, payload);
    }
}
