package com.flipkart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties properties;

    public ReadConfig() {
        File src = new File("./configurations/config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);
        }
        catch (Exception e) {
            throw new RuntimeException("Exception is: " + e.getMessage());
        }

    }

    public String getChromeDriverPath() {
        String driver = properties.getProperty("chromeDriverPath");
        return driver;
    }

    public Integer getWaitTime(){
        Integer wtime= Integer.valueOf(properties.getProperty("waitTime"));
        return wtime  ;
    }

    public String getFireFoxDriverPath() {
        String driver = properties.getProperty("fireFoxDriverPath");
        return driver;
    }

    public String getBrsName() {
        String driver = properties.getProperty("brs");
        return driver;
    }

    public String getBaseUrl() {
        String url = properties.getProperty("baseURL");
        return url;
    }

    public String getUser() {
        String user = properties.getProperty("validUser");
        return user;
    }

    public String getPass() {
        String pass = properties.getProperty("validPassword");
        return pass;
    }

    public String getTestDataFilePath() {
        String tdFilepath = properties.getProperty("testDataFilePath");
        return tdFilepath;
    }
}
