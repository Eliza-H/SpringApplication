package com.example.spring.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by elh on 20.09.17.
 */
@Builder
@Getter
public class AnnouncementDTO {
    private Long id;
    private String title;
    private String description;
    private boolean isLiked;
}
