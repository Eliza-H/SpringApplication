package com.example.spring.dao;

import com.example.spring.model.User;
import java.util.List;

public interface UserDao {

	void save(User user);

	User findById(int id);

	User findByLogin(String login);

    List<User> listOfUsers();
}


