package com.qaiware.messageservice.core.converter;

public interface DomainObjectConverter<T,V> {

    T toDomain(V entity);

    V toEntity(T domainObject);
}
