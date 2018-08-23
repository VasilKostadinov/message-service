package com.qaiware.messageservice.config;

import com.qaiware.messageservice.core.converter.DomainObjectConverter;
import com.qaiware.messageservice.core.converter.MessageConverter;
import com.qaiware.messageservice.core.factory.MessageFactory;
import com.qaiware.messageservice.core.factory.PropertiesConfigMessageFactory;
import com.qaiware.messageservice.core.message.AbstractMessage;
import com.qaiware.messageservice.core.message.EmotionMessage;
import com.qaiware.messageservice.core.message.TextMessage;
import com.qaiware.messageservice.core.repository.MessageRepository;
import com.qaiware.messageservice.core.repository.MessageRepositoryImpl;
import com.qaiware.messageservice.core.validator.MessageCharacterRule;
import com.qaiware.messageservice.core.validator.MessageLengthRule;
import com.qaiware.messageservice.core.validator.MessageValidator;
import com.qaiware.messageservice.core.validator.ValidationRule;
import com.qaiware.messageservice.dal.dao.MessageDAO;
import com.qaiware.messageservice.dal.dao.MessageDAOImpl;
import com.qaiware.messageservice.dal.entity.MessageEntity;
import com.qaiware.messageservice.presentation.web.handler.MessageHandler;
import com.qaiware.messageservice.presentation.web.handler.MessageHandlerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableTransactionManagement
public class MessageServiceAppConfig {

    @Value("${message.mappings:message-mappings.properties}")
    private String messageMappings;

    @Bean
    public MessageValidator messageValidator(){
        MessageValidator messageValidator = new MessageValidator();
        messageValidator.registerRule(TextMessage.class,new MessageLengthRule(1,160));
        messageValidator.registerRule(EmotionMessage.class,new MessageLengthRule(2,10));
        messageValidator.registerRule(EmotionMessage.class,new MessageCharacterRule('0','9'));
        return messageValidator;
    }

    @Bean
    public MessageDAO messageDAO(){
        return new MessageDAOImpl();
    }

    @Bean
    public MessageRepository messageRepository(){
        return new MessageRepositoryImpl(messageDAO(),messageObjectConverter());
    }

    @Bean
    public DomainObjectConverter<AbstractMessage,MessageEntity> messageObjectConverter(){
        return new MessageConverter(this.messageFactory());
    }

    @Bean
    public MessageHandler messageHandler(){
        return new MessageHandlerImpl(this.messageValidator(),this.messageRepository(),this.messageFactory());
    }

    @Bean
    public MessageFactory messageFactory(){
        return new PropertiesConfigMessageFactory(this.messageMappings);
    }
}
