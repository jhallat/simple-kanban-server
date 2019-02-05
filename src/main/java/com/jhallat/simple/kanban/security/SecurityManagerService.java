package com.jhallat.simple.kanban.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jhallat.simple.kanban.model.User;
import com.jhallat.simple.kanban.repository.UserRepository;

@Component
public class SecurityManagerService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean validateUser(User user) {

		User authorizedUser = userRepository.findByUsername(user.getUsername());
		
		return authorizedUser != null && authorizedUser.getPassword().equals(user.getPassword()); 
	}
	
}
