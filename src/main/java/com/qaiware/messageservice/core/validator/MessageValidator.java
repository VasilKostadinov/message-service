package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.message.EmotionMessage;
import com.qaiware.messageservice.core.message.TextMessage;

import java.util.*;
import java.util.stream.Collectors;

public class MessageValidator {

    private Map<Class<? extends AbstractMessage>, List<ValidationRule>> typeToRuleMap = new HashMap<>();

    public MessageValidator(){

    }

    public MessageValidator(Map<Class<? extends AbstractMessage>, List<ValidationRule>> typeToRuleMap){
        Objects.requireNonNull(typeToRuleMap,"TypeRule map can not be null !");
        this.typeToRuleMap.putAll(typeToRuleMap);
    }

    public ValidationChain validateMessage(AbstractMessage message){
        List<ValidationRule> validationRules = typeToRuleMap.get(message.getClass());
        if (validationRules != null){
            List<ValidationResult> validationResults = validationRules.stream().map(r-> r.validateMessage(message)).collect(Collectors.toList());
            return new ValidationChain(validationResults);
        }
        return new ValidationChain(true);
    }

    public void registerRule(Class<? extends AbstractMessage> messageClass, ValidationRule rule){
        List<ValidationRule> rules = typeToRuleMap.get(messageClass);
        if (rules == null){
            rules = new ArrayList<>();
            this.typeToRuleMap.put(messageClass,rules);
        }
        rules.add(rule);
    }
}
