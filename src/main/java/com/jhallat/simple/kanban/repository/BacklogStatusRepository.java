package com.jhallat.simple.kanban.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.BacklogStatus;

public interface BacklogStatusRepository extends JpaRepository<BacklogStatus, Integer> {

	Optional<BacklogStatus> findByCode(String string);

}
