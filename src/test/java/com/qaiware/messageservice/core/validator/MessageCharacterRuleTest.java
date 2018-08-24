package com.qaiware.messageservice.core.validator;


import com.qaiware.messageservice.core.message.AbstractMessage;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageCharacterRuleTest {

    private static int MIN_MSG_LENGTH = 0;
    private static int MAX_MSG_LENGTH = 250;

    private static int MIN_RULE_LENGTH = 25;
    private static int MAX_RULE_LENGTH = 100;

    private MessageLengthRule messageLengthRule = new MessageLengthRule(MIN_RULE_LENGTH,MAX_RULE_LENGTH);

    AbstractMessage testMessage = mock(AbstractMessage.class);

    @Test
    public void shouldValidateMessageInRange(){
        boolean isValid = true;
        for (int i = MIN_RULE_LENGTH ; i <= MAX_RULE_LENGTH; i++) {
            mockTestMessage(generateStringWithLength(i));
            isValid = isValid && messageLengthRule.validateMessage(this.testMessage).isValid();
        }

        assertTrue("Validator invalidated valid message.",isValid);
    }

    @Test
    public void shouldNotValidateMessageOutsideRange(){
        boolean isNotValid = true;
        for (int i = MIN_MSG_LENGTH; i < MIN_RULE_LENGTH; i++) {
            mockTestMessage(generateStringWithLength(i));
            isNotValid = isNotValid && !messageLengthRule.validateMessage(this.testMessage).isValid();
        }
        for (int i = MAX_RULE_LENGTH + 1; i <= MAX_MSG_LENGTH; i++) {
            mockTestMessage(generateStringWithLength(i));
            isNotValid = isNotValid && !messageLengthRule.validateMessage(this.testMessage).isValid();
        }

        assertTrue("Validator validated invalid message.",isNotValid);
    }

    private String generateStringWithLength(int stringLength){
        char[] charArray = new char[stringLength];
        Arrays.fill(charArray, 'a');
        return new String(charArray);
    }

    private void mockTestMessage(String message){
        when(this.testMessage.getPayload()).thenReturn(message);
    }
}
