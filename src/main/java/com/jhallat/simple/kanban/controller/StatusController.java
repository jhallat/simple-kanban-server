package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.Status;
import com.jhallat.simple.kanban.repository.StatusRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
public class StatusController {
	
	private final static Logger logger = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired
	private StatusRepository statusRepository;
	
	@GetMapping("statuses")
	@ApiOperation(value="Find statuses associated with a category",
	              notes="Returns all status values associated with the category .")
	public ResponseEntity<List<Status>> getBacklogStatuses(@RequestParam("category") String category) {
		List<Status> statuses =  statusRepository.findByCategory(category);
		if (statuses.isEmpty()) {
			logger.info(String.format("Statuses not found for category '%s'", category));
			return new ResponseEntity<>(statuses, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(statuses, HttpStatus.OK);
		}
	}
	
}
