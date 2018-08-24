package com.qaiware.messageservice.core.validator;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class MessageCharacterRuleTest extends AbstractMessageRuleTest {

    private static final Character START_CHAR = ' ';
    private static final Character END_CHAR = '~';

    private static Character START_CHAR_RULE = 'a';
    private static Character END_CHAR_RULE = 'z';

    private MessageCharacterRule messageCharacterRule = new MessageCharacterRule(START_CHAR_RULE,END_CHAR_RULE);


    @Test
    public void shouldValidateCharactersOutsideRange(){
        boolean isValid = true;
        for (int i = START_CHAR; i < START_CHAR_RULE; i++) {
            String toTest = String.valueOf((char)i);
            mockTestMessage(toTest);
            isValid = isValid && messageCharacterRule.validateMessage(this.testMessage).isValid();
        }
        for (int i = END_CHAR_RULE + 1; i <= END_CHAR; i++) {
            String toTest = String.valueOf((char)i);
            mockTestMessage(toTest);
            isValid = isValid && messageCharacterRule.validateMessage(this.testMessage).isValid();
        }

        assertTrue("Validator invalidated valid message.",isValid);
    }

    @Test
    public void shouldNotValidateCharactersInRange(){
        boolean isNotValid = true;
        for (int i = START_CHAR_RULE ; i <= END_CHAR_RULE; i++) {
            String toTest = String.valueOf((char)i);
            mockTestMessage(toTest);
            isNotValid = isNotValid && !messageCharacterRule.validateMessage(this.testMessage).isValid();
        }

        assertTrue("Validator validated invalid message.",isNotValid);
    }

}
