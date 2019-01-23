package com.jhallat.simple.kanban.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.WorkflowStatus;

public interface WorkflowStatusRepository extends JpaRepository<WorkflowStatus, Integer> {

	Optional<WorkflowStatus> findByCode(String string);

}
