package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by elh on 17.09.17.
 */

@Entity
@Table(name = "SERVICES")
@Setter
@Getter
@NoArgsConstructor
public class ServiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "SERVICE_ITEM_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "FILENAME")
    private String filename;

    public ServiceItem(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public ServiceItem(String title, String description, String filename) {
        this.title = title;
        this.description = description;
        this.filename = filename;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceItems")
    @Getter
    @Setter
    private Set<Likes> likes = new HashSet<Likes>();

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "serviceItems")
//    @Getter
//    @Setter
//    private Set<User> users = new HashSet<User>();


}
