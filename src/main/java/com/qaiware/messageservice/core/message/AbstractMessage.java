package com.qaiware.messageservice.core.message;

import java.time.LocalDateTime;

public abstract class AbstractMessage {

    private Integer id;

    private String type;

    private String payload;

    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    protected AbstractMessage(Integer id, String type, String payload, LocalDateTime createdAt){
        this.id = id;
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    protected AbstractMessage(String type, String payload){
        this(null,type,payload,LocalDateTime.now());
    }
}
