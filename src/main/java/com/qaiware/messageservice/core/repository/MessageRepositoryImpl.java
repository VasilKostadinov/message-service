package com.qaiware.messageservice.core.repository;

import com.qaiware.messageservice.dal.dao.GenericDAO;
import com.qaiware.messageservice.dal.entity.MessageEntity;
import com.qaiware.messageservice.core.converter.DomainObjectConverter;
import com.qaiware.messageservice.core.message.AbstractMessage;

public class MessageRepositoryImpl extends AbstractDAORepository<AbstractMessage,MessageEntity> implements MessageRepository {

    public MessageRepositoryImpl(GenericDAO<MessageEntity> dao,DomainObjectConverter<AbstractMessage,MessageEntity> objectConverter) {
        super(dao,objectConverter);
    }
}
