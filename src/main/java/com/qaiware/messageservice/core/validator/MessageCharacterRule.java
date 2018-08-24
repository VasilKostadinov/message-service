package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.AbstractMessage;

import java.text.MessageFormat;
import java.util.*;

public class MessageCharacterRule implements ValidationRule {

    private Set<Character> notAllowedChars = new HashSet<>();

    public MessageCharacterRule(List<Character> charList){
        Objects.requireNonNull(charList,"CharList can not be null !");
        this.notAllowedChars.addAll(charList);
    }

    public MessageCharacterRule(Character startCharRange,Character endCharRange){
        this.notAllowedChars.addAll(getAllCharactersInRange(startCharRange,endCharRange));
    }

    @Override
    public ValidationResult validateMessage(AbstractMessage message) {
        String payLoad = message.getPayload();
        boolean containsForbiddenChars = notAllowedChars.stream().anyMatch(c-> payLoad.indexOf(c)  > -1);
        if (!containsForbiddenChars){
            return ValidationResult.valid();
        }
        return ValidationResult.inValid(MessageFormat.format("Message contains some of the forbidden characters {0} !", Arrays.toString(notAllowedChars.toArray())));
    }

    private static List<Character> getAllCharactersInRange(Character startCharRange, Character endCharRange){
        List<Character> characterList = new ArrayList<>();
        Character temp = startCharRange;
        while (temp != endCharRange + 1){
            characterList.add(temp);
            temp++;
        }
        return characterList;
    }
}
