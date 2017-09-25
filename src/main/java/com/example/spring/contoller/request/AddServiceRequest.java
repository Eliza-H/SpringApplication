package com.example.spring.contoller.request;

import com.example.spring.model.AddServiceUserParameters;
import lombok.Getter;

import javax.validation.constraints.NotNull;


/**
 * Created by elh on 17.09.17.
 */
@Getter
public class AddServiceRequest implements AddServiceUserParameters {
    @NotNull
    private String title;
    @NotNull
    private String description;
}
