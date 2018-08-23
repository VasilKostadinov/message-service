package com.qaiware.messageservice.core.factory;

import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.util.PropertyFileUtils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PropertiesConfigMessageFactory implements MessageFactory{

    private String fileMappingPath;
    private Map<String, Class<? extends AbstractMessage>> messageMappings;

    public PropertiesConfigMessageFactory(String mappingsPath){
        this.fileMappingPath = mappingsPath;
        this.messageMappings = new HashMap<>();
        this.initializeMessages();
    }

    private void initializeMessages() {
        Properties properties = PropertyFileUtils.readPropertiesFile(this.fileMappingPath);
        for (String messageType : properties.stringPropertyNames()) {
            String className = properties.getProperty(messageType);
            try {
                Class clazz = Class.forName(className);
                this.messageMappings.put(messageType,clazz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(MessageFormat.format("Message type is not supported yet {0}",messageType),e);
            }
        }
    }


    @Override
    public AbstractMessage createMessage(String type, String payLoad) {
        return createInstanceWithArgs(type,payLoad);
    }

    @Override
    public AbstractMessage createMessage(int id, String type, String payLoad, LocalDateTime createdAt) {
        return createInstanceWithArgs(type,id,type,payLoad,createdAt);
    }

    private AbstractMessage createInstanceWithArgs(String type,Object... params){
        Class<?>[] classes = Arrays.asList(params).stream().map(p-> p.getClass()).toArray(Class<?>[]::new);
        Class<? extends AbstractMessage> clazz = this.messageMappings.get(type);
        Objects.requireNonNull(clazz,MessageFormat.format("This type of message is not supported yet {0}",type));
        AbstractMessage instance;
        try {
            instance = clazz.getConstructor(classes).newInstance(params);
        } catch (Exception e){
            throw new RuntimeException(MessageFormat.format("No such constructor in class {0}",clazz.getCanonicalName()),e);
        }
        return instance;
    }
}
