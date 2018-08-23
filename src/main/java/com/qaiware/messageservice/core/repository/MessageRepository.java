package com.qaiware.messageservice.core.repository;

import com.qaiware.messageservice.core.message.AbstractMessage;

public interface MessageRepository extends GenericRepository<AbstractMessage> {

    void save(AbstractMessage message);
}
