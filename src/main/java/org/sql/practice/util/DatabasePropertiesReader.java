package org.sql.practice.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabasePropertiesReader {

    public static Map<String, String> loadProperties() {
        Map<String, String> properties = new HashMap<>();
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
            props.load(fis);
            fis.close();
            String databaseUrl = props.getProperty("database.url");
            String databaseSchema = props.getProperty("database.schema");
            String databaseUsername = props.getProperty("database.username");
            String databasePassword = props.getProperty("database.password");

            properties.put("url", databaseUrl);
            properties.put("schema", databaseSchema);
            properties.put("username", databaseUsername);
            properties.put("password", databasePassword);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
