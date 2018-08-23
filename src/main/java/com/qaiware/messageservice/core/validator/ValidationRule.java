package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.AbstractMessage;

public interface ValidationRule {

    ValidationResult validateMessage(AbstractMessage message);
}
