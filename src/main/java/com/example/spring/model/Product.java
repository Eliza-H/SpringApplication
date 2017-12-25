package com.example.spring.model;

import com.example.spring.contoller.storage.StorageService;
import com.example.spring.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@Entity
@Table(name = "Product")
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long creatorId;

    private String title;

    private String description;

    private String imageName;

    private int price;

    private LocalDateTime creationDate;

    public Product(final String title, final String description, final String img, final int price) {
        this.title = title;
        this.description = description;
        this.imageName = img;
        this.price = price;
        this.creationDate = LocalDateTime.now();
        this.creatorId = Utils.getCurrentUser().get().getId();
    }
}
