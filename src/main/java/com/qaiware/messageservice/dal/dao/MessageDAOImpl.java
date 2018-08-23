package com.qaiware.messageservice.dal.dao;

import com.qaiware.messageservice.dal.entity.MessageEntity;
import com.qaiware.messageservice.dal.exception.DataAccessException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MessageDAOImpl implements MessageDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MessageDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public MessageDAOImpl(){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MessageEntity message) {
        try{
            entityManager.persist(message);
        } catch (HibernateException exception){
            throw new DataAccessException(exception.getMessage(),exception);
        }
    }

}
