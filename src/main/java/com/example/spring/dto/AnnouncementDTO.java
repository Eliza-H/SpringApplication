package com.example.spring.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

/**
 * Created by elh on 20.09.17.
 */
@Builder

public class AnnouncementDTO {
    @Getter
    private Long id;

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private boolean isLiked;

    private String filename;

    public String getFilename() {
//        if (filename == null) {
//            return Optional.empty();
//        }
//        return Optional.of("/static/" + filename);
//    }
    return filename == null ? null : "/static/" + filename;}
}
