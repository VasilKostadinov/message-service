package com.qaiware.messageservice.presentation.web.controller;


import com.qaiware.messageservice.dal.exception.DataAccessException;
import com.qaiware.messageservice.presentation.web.handler.MessageHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageHandler messageHandler;

    public MessageController(MessageHandler messageHandler){
        this.messageHandler = messageHandler;
    }

    @Transactional
    @RequestMapping(value = "/{type}",method = RequestMethod.POST)
    public ResponseEntity<String> processMessage(@RequestParam(name="payload", required=false, defaultValue="") String payLoad,
                                                 @PathVariable(name = "type") String type) {
        return this.messageHandler.handleMessage(type,payLoad);
    }
}
