package com.qaiware.messageservice.core.repository;

import com.qaiware.messageservice.dal.dao.GenericDAO;
import com.qaiware.messageservice.core.converter.DomainObjectConverter;

public abstract class AbstractDAORepository<T, E> implements GenericRepository<T> {

    protected final GenericDAO<E> dao;
    protected final DomainObjectConverter<T,E> objectConverter;

    protected AbstractDAORepository(GenericDAO<E> dao,DomainObjectConverter<T,E> objectConverter){
        this.dao = dao;
        this.objectConverter = objectConverter;
    }

    @Override
    public void save(T object){
        E entity = objectConverter.toEntity(object);
        this.dao.save(entity);
    }
}
