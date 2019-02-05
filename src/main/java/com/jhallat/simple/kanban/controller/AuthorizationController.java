package com.jhallat.simple.kanban.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jhallat.simple.kanban.model.ApiResponse;
import com.jhallat.simple.kanban.model.User;
import com.jhallat.simple.kanban.repository.UserRepository;
import com.jhallat.simple.kanban.security.JwtTokenProvider;
import com.jhallat.simple.kanban.security.SecurityManagerService;


@RestController
@RequestMapping("api/v1")
public class AuthorizationController {

	@Autowired
	private SecurityManagerService securityManager;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	 
    @Autowired
    private JwtTokenProvider tokenProvider;	 
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@PostMapping("user/login")
	public ResponseEntity<String> login(@RequestBody User user) {	
		
		boolean authorized = securityManager.validateUser(user);
		
		if (authorized) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							user.getUsername(),
							user.getPassword()
							)
					);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(authentication);
			return ResponseEntity.ok(jwt);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	} 
	
	@PostMapping("user")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody User requestedUser) {
		
		if (userRepository.existsByUsername(requestedUser.getUsername())) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Username already exists"), HttpStatus.BAD_REQUEST);
		}
		
		String password = passwordEncoder.encode(requestedUser.getPassword());
		User user = new User();
		user.setUsername(requestedUser.getUsername());
		user.setPassword(password);
		
		User result = userRepository.save(user);
		
		//TODO implement API
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfuly"));
	}
	
}
