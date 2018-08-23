package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.AbstractMessage;

import java.text.MessageFormat;

public class MessageLengthRule implements ValidationRule {

    private int start;
    private int end;

    public MessageLengthRule(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public ValidationResult validateMessage(AbstractMessage message) {
        String msg = message.getPayload();
        if (msg.length() > start && msg.length() < end){
            return ValidationResult.valid();
        }
        return ValidationResult.inValid(MessageFormat.format("Message length is not in the required boundaries {0} to {1}",start,end));
    }
}
