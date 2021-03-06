package com.jhallat.simple.kanban.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.Goal;
import com.jhallat.simple.kanban.repository.GoalRepository;
import com.jhallat.simple.kanban.security.CurrentUserUtility;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
public class GoalController {
	
	@Autowired
	private CurrentUserUtility currentUserUtility;
	
	@Autowired
	private GoalRepository goalRepository;
	
	@GetMapping("goals")
	public List<Goal> getGoals(@RequestHeader HttpHeaders headers) {
		
		long userId = currentUserUtility.getCurrentUserId(headers);
		return goalRepository.findByUserId(userId);
	}
	
	@PostMapping("goals")
	@ApiOperation(value="Add a goal",
    notes="Adds a goal and returns the newly created goal. If the creation date has not been set by the caller, the " + 
          "creation date will be set to the current date")
	public Goal addGoal(@Valid @RequestBody Goal goal, @RequestHeader HttpHeaders headers) {
		long userId = currentUserUtility.getCurrentUserId(headers);
		if (goal.getCreationDate() == null) {
			//TODO Should make a copy instead of modifying the parameter
			goal.setCreationDate(LocalDate.now());
		}
		goal.setUserId(userId);
		return goalRepository.saveAndFlush(goal);
	}
	
	@PutMapping("goals/{id}")
	public Goal updateGoal(@PathVariable("id") int id, @RequestBody Goal goal, @RequestHeader HttpHeaders headers) {
		
		long userId = currentUserUtility.getCurrentUserId(headers);
		Optional<Goal> searchResult = goalRepository.findById(id);
		if (searchResult.isPresent()) {
			Goal currentGoal = searchResult.get();
			currentGoal.setAlternatives(goal.getAlternatives());
			currentGoal.setDescription(goal.getDescription());
			currentGoal.setExpectation(goal.getExpectation());
			currentGoal.setStatusId(goal.getStatusId());
			currentGoal.setStrategy(goal.getStrategy());
			currentGoal.setPriority(goal.getPriority());
			currentGoal.setUserId(userId);
			return goalRepository.saveAndFlush(currentGoal);
		} else {
			//TODO This should return an error
			return addGoal(goal, headers);
		}
		
	}

}
