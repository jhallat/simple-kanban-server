package com.jhallat.simple.kanban.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	List<Status> findByCategory(String category);

	Optional<Status> findByCategoryAndCode(String backlog, String string);

}
