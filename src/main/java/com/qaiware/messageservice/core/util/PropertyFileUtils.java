package com.qaiware.messageservice.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.function.Consumer;

public class PropertyFileUtils {

    public static void useInputStream(String filePath, Consumer<InputStream> action){
        try(InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
            action.accept(stream);
        } catch (IOException e) {
            throw new RuntimeException(MessageFormat.format("Could not load {0} ",filePath),e);
        }
    }

    public static Properties readPropertiesFile(String filePath){
        Properties properties = new Properties();

        useInputStream(filePath,s-> {
            try {
                properties.load(s);
            } catch (IOException e) {
                throw new RuntimeException(MessageFormat.format("Problem while loading properties file {0} ",filePath),e);
            }
        });

        return properties;
    }
}
