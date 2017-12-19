package com.example.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by elh on 10.09.17.
 */
@Entity
@Getter
@Table(name = "users")
public class User {

    public User() {
    }

    public static User getInstance(UserParameters userParameters) {
        final User user = new User();
        user.email = userParameters.getEmail();
        user.lastName = userParameters.getLastName();
        user.firstName = userParameters.getFirstName();
        user.login = userParameters.getLogin();
        return user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long id;

    @Setter
    @NotEmpty
    @Column(name = "LOGIN", length = 100, nullable = false, unique = true)
    private String login;

    @Setter
    @NotEmpty
    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @Setter
    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Setter
    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Setter
    @NotEmpty
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "STATE", nullable = false)
    private String state = State.ACTIVE.getState();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    @Setter
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.valueOf(id.intValue());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return  result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles + "]";
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @Getter
    @Setter
    private Set<Likes> likes = new HashSet<Likes>();

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "Likes", joinColumns = {
//            @JoinColumn(name = "USER_ID")},
//            inverseJoinColumns = { @JoinColumn(name = "SERVICE_ITEM_ID") })
//
//    @Setter
//    public Set<ServiceItem> serviceItems = new HashSet<ServiceItem>();

}