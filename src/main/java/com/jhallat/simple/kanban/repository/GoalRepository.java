package com.jhallat.simple.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

}
