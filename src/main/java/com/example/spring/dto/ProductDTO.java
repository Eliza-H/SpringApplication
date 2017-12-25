package com.example.spring.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@Getter
@Builder
public class ProductDTO {

    private Long id;

    private String title;

    private String description;

    private String img;

    private int price;

}
