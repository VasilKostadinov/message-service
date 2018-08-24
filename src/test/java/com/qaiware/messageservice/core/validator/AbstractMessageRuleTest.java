package com.qaiware.messageservice.core.validator;

import com.qaiware.messageservice.core.message.AbstractMessage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractMessageRuleTest {

    AbstractMessage testMessage = mock(AbstractMessage.class);

    protected final void mockTestMessage(String message){
        when(this.testMessage.getPayload()).thenReturn(message);
    }
}
