package com.example.spring.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="USER_PROFILE")
@NoArgsConstructor
public class UserProfile {

	public static UserProfile getInstance(UserProfileType userProfileType) {
		return new UserProfile(userProfileType);
	}

	public UserProfile(UserProfileType userProfileType) {
		type = userProfileType.getUserProfileType();
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	

	@Column(name="TYPE", length=15, nullable=false)
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserProfile))
			return false;
		UserProfile other = (UserProfile) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ",  type=" + type	+ "]";
	}
	

}
