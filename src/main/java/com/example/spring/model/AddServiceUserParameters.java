package com.example.spring.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by elh on 17.09.17.
 */
public interface AddServiceUserParameters {
    String getTitle();
    String getDescription();
    String getFilename();
}
