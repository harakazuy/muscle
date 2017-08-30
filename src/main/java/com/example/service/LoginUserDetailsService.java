package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;
import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	public UserDetails loadUserByName(String name) throws UsernameNotFoundException{
		User user = userRepository.findOne(name);
		if(user == null){
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new LoginUserDetails(user);
	}
}
