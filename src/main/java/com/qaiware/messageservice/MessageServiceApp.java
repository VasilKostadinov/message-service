package com.qaiware.messageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class MessageServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApp.class, args);
    }
}
