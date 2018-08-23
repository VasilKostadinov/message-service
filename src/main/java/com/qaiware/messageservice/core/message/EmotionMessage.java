package com.qaiware.messageservice.core.message;

public class EmotionMessage extends AbstractMessage {

    public static final String SEND_EMOTION = "send_emotion";

    public EmotionMessage(String payload) {
        super(SEND_EMOTION, payload);
    }
}
