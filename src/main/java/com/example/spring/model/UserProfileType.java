package com.example.spring.model;

public enum UserProfileType {
	BUYER("BUYER"),
	SELLER("SELLER"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
