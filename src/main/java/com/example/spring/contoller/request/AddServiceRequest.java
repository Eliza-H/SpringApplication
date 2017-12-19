package com.example.spring.contoller.request;

import com.example.spring.model.AddServiceUserParameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;


/**
 * Created by elh on 17.09.17.
 */
@Getter
@Setter
@AllArgsConstructor
public class AddServiceRequest implements AddServiceUserParameters {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String filename;


}
