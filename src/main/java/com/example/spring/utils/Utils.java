package com.example.spring.utils;

import com.example.spring.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
public class Utils {

    public static Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder
            .getContext().getAuthentication();

        if (auth != null) {

            Object principal = auth.getPrincipal();

            if (principal instanceof User) // User is your user type that implements UserDetails
                return Optional.of((User) principal);
        }

        return Optional.empty();
    }

}
