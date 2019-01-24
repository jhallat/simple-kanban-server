package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.Status;
import com.jhallat.simple.kanban.repository.StatusRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
public class BacklogStatusController {

	private static final String CATEGORY = "backlog";
	
	@Autowired
	private StatusRepository statusRepository;
	
	@GetMapping("backlog-statuses")
	@ApiOperation(value="Find backlog statuses",
	              notes="Returns all status values related to backlog tasks.")
	public ResponseEntity<List<Status>> getBacklogStatuses() {
		List<Status> statuses =  statusRepository.findByCategory(CATEGORY);
		if (statuses.isEmpty()) {
			return new ResponseEntity<>(statuses, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(statuses, HttpStatus.OK);
		}
	}
	
}
