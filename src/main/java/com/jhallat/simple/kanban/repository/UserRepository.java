package com.jhallat.simple.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.User;



public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	public boolean existsByUsername(String username);
}
