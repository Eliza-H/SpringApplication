package com.example.spring.contoller.request;

import com.example.spring.model.UserParameters;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by elh on 16.09.17.
 */

@Builder
@Getter
public class UserRequest implements UserParameters {
    private String login;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

}
