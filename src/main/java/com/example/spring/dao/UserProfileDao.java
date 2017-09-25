package com.example.spring.dao;

import com.example.spring.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
