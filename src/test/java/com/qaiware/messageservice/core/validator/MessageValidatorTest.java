package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.EmotionMessage;
import com.qaiware.messageservice.core.message.TextMessage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MessageValidatorTest {

    private static final int EMOTION_MSG_MIN_LEN = 3;
    private static final int EMOTION_MSG_MAX_LEN = 10;
    private static final char EMOTION_MSG_MIN_CHAR = '0';
    private static final char EMOTION_MSG_MAX_CHAR = '9';
    private static final int TEXT_MSG_MIN_LEN = 0;
    private static final int TEXT_MSG_MAX_LEN = 10;
    private static final char TEXT_MSG_MIN_CHAR = 'a';
    private static final char TEXT_MSG_MAX_CHAR = 'f';

    private MessageValidator messageValidator = new MessageValidator();

    public MessageValidatorTest(){
        registerValidatorRules();
    }

    @Test
    public void shouldReturnValidChainForEmotionMessage(){
        EmotionMessage emotionMessage = new EmotionMessage("aaabvz~~ew");
        boolean isValid = messageValidator.validateMessage(emotionMessage).isValid();

        assertTrue("Invalid chain for valid messages.",isValid);
    }

    @Test
    public void shouldNotReturnValidChainForEmotionMessage(){
        EmotionMessage emotionMessageWrongChars = new EmotionMessage("12345");
        EmotionMessage emotionMessageWrongLength = new EmotionMessage("aa");
        boolean isNotValid = !messageValidator.validateMessage(emotionMessageWrongChars).isValid();
        isNotValid = isNotValid && !messageValidator.validateMessage(emotionMessageWrongLength).isValid();

        assertTrue("Valid chain for invalid messages.",isNotValid);
    }

    @Test
    public void shouldReturnValidChainForTextMessage(){
        TextMessage textMessage = new TextMessage("zzzzvz~~xw");
        boolean isValid = messageValidator.validateMessage(textMessage).isValid();

        assertTrue("Invalid chain for valid messages.",isValid);
    }

    @Test
    public void shouldReturnInValidChainForTextMessage(){
        TextMessage textMessageWrongChars = new TextMessage("fff");
        TextMessage textMessageWrongLength = new TextMessage("ffffffffffffffff");
        boolean isNotValid = !messageValidator.validateMessage(textMessageWrongChars).isValid();
        isNotValid = isNotValid && !messageValidator.validateMessage(textMessageWrongLength).isValid();

        assertTrue("Invalid chain for valid messages.",isNotValid);
    }

    private void registerValidatorRules() {
        this.messageValidator.registerRule(EmotionMessage.class,new MessageLengthRule(EMOTION_MSG_MIN_LEN,EMOTION_MSG_MAX_LEN));
        this.messageValidator.registerRule(EmotionMessage.class,new MessageCharacterRule(EMOTION_MSG_MIN_CHAR,EMOTION_MSG_MAX_CHAR));
        this.messageValidator.registerRule(TextMessage.class,new MessageLengthRule(TEXT_MSG_MIN_LEN,TEXT_MSG_MAX_LEN));
        this.messageValidator.registerRule(TextMessage.class,new MessageCharacterRule(TEXT_MSG_MIN_CHAR,TEXT_MSG_MAX_CHAR));
    }
}
