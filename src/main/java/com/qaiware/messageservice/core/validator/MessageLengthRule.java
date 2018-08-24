package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.AbstractMessage;

import java.text.MessageFormat;

public class MessageLengthRule implements ValidationRule {

    private int min;
    private int max;

    public MessageLengthRule(int min, int max){
        this.min = min;
        this.max = max;
    }

    @Override
    public ValidationResult validateMessage(AbstractMessage message) {
        String msg = message.getPayload();
        if (msg.length() >= min && msg.length() <= max){
            return ValidationResult.valid();
        }
        return ValidationResult.inValid(MessageFormat.format("Message length is not in the required boundaries {0} to {1}",min,max));
    }
}
