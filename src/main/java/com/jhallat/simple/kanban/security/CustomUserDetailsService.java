package com.jhallat.simple.kanban.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhallat.simple.kanban.model.User;
import com.jhallat.simple.kanban.model.UserPrincipal;
import com.jhallat.simple.kanban.repository.UserRepository;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>()); 
		
		return userPrincipal;
	}
	
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
	
}