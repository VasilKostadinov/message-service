package com.qaiware.messageservice.core.factory;

import com.qaiware.messageservice.core.message.AbstractMessage;

import java.time.LocalDateTime;

public interface MessageFactory {

    AbstractMessage createMessage(String type, String payLoad);

    AbstractMessage createMessage(int id, String type, String payLoad, LocalDateTime createdAt);
}
