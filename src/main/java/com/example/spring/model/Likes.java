package com.example.spring.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by elh on 26.09.17.
 */


@Setter
@Getter
@Entity
@Table(name = "Likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @Getter
    @Setter
    private User users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_ITEM_ID", nullable = false)
    @Getter
    @Setter
    private ServiceItem serviceItems;

}

