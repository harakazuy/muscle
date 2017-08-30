package com.example.repository;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	public User findOne(String name) {
		User user = new User("name", "password", null);
		return user;
	}
}
