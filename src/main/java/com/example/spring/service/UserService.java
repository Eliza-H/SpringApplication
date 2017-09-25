package com.example.spring.service;

/**
 * Created by elh on 10.09.17.
 */
import com.example.spring.model.User;
import com.example.spring.model.UserParameters;

import java.util.List;

public interface UserService {
    void save(UserParameters user);

    User findById(int id);

    User findByLogin(String sso);

    List<User> listOfUsers();

}