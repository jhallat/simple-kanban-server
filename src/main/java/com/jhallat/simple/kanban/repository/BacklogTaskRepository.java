package com.jhallat.simple.kanban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.BacklogTask;

public interface BacklogTaskRepository extends JpaRepository<BacklogTask, Integer>{

	List<BacklogTask> findByUserId(long userId);
	

}
