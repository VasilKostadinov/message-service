package com.qaiware.messageservice.core.converter;

import com.qaiware.messageservice.core.factory.MessageFactory;
import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.dal.entity.MessageEntity;

public class MessageConverter implements DomainObjectConverter<AbstractMessage,MessageEntity> {

    private MessageFactory messageFactory;

    public MessageConverter(MessageFactory factory){
        this.messageFactory = factory;
    }

    @Override
    public AbstractMessage toDomain(MessageEntity messageEntity) {
        return this.messageFactory.createMessage(messageEntity.getId(),messageEntity.getType(),messageEntity.getPayload(),messageEntity.getCreatedAt());
    }

    @Override
    public MessageEntity toEntity(AbstractMessage message) {
        return new MessageEntity(message.getId(),message.getType(),message.getPayload(),message.getCreatedAt());
    }
}
