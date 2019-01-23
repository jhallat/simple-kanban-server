package com.jhallat.simple.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.WorkflowTask;

public interface WorkflowTaskRepository extends JpaRepository<WorkflowTask, Integer> {

}
