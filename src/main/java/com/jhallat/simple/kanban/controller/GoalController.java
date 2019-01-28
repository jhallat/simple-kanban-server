package com.jhallat.simple.kanban.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("goals/{id}")
	public Goal updateGoal(@PathVariable("id") int id, @RequestBody Goal goal) {
		
		Optional<Goal> searchResult = goalRepository.findById(id);
		if (searchResult.isPresent()) {
			Goal currentGoal = searchResult.get();
			currentGoal.setAlternatives(goal.getAlternatives());
			currentGoal.setDescription(goal.getDescription());
			currentGoal.setExpectation(goal.getExpectation());
			currentGoal.setStatusId(goal.getStatusId());
			currentGoal.setStrategy(goal.getStrategy());
			return goalRepository.saveAndFlush(currentGoal);
		} else {
			//TODO This should return an error
			return addGoal(goal);
		}
		
	}

}
