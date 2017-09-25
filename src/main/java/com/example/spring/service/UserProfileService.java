package com.example.spring.service;

import com.example.spring.model.UserProfile;

import java.util.List;

public interface UserProfileService {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
