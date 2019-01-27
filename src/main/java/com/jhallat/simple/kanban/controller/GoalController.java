package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.Goal;
import com.jhallat.simple.kanban.repository.GoalRepository;

@RestController
@RequestMapping("api/v1")
public class GoalController {
	
	@Autowired
	private GoalRepository goalRepository;
	
	@GetMapping("goals")
	public List<Goal> getGoals() {
		return goalRepository.findAll();
	}
	
	@PostMapping("goals")
	public Goal addGoal(@RequestBody Goal goal) {
		return goalRepository.saveAndFlush(goal);
	}

}