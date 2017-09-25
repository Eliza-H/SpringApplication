package com.example.spring.contoller.request;

import com.example.spring.model.LikeParameters;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Created by elh on 20.09.17.
 */

@Getter
public class LikeRequest implements LikeParameters {
    @NotNull
    private Long userId;
    @NotNull
    private Long serviceId;
}
