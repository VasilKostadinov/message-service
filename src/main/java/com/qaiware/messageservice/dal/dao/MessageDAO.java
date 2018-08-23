package com.qaiware.messageservice.dal.dao;

import com.qaiware.messageservice.dal.entity.MessageEntity;

public interface MessageDAO extends GenericDAO<MessageEntity> {

    void save(MessageEntity message);
}
