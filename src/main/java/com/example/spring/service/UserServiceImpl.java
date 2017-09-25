package com.example.spring.service;

/**
 * Created by elh on 10.09.17.
 */
import com.example.spring.model.UserParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.dao.UserDao;
import com.example.spring.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void save(UserParameters userParameters){
        User user = User.getInstance(userParameters);
        user.setPassword(passwordEncoder.encode(userParameters.getPassword()));
        dao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(int id) {
        return dao.findById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public User findByLogin(String login) {
        return dao.findByLogin(login);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> listOfUsers() {
        return dao.listOfUsers();
    }
}