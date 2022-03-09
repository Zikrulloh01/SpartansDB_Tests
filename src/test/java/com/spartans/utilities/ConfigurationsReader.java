package com.spartans.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * reads the properties file configuration.properties
 */
public class ConfigurationsReader {

    private static Properties properties;

    static {

        try {
            String path = "configurations.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }

}