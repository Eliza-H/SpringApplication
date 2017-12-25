package com.example.spring.contoller.storage;

/**
 * Created by elh on 27.09.17.
 */
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = System.getProperty("user.dir") + "/src/main/resources/static/upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}