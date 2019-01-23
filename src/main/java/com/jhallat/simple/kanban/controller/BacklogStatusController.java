package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.Status;
import com.jhallat.simple.kanban.repository.StatusRepository;

@RestController
@RequestMapping("api/v1")
public class BacklogStatusController {

	private static final String CATEGORY = "backlog";
	
	@Autowired
	private StatusRepository statusRepository;
	
	@GetMapping("backlog-statuses")
	public List<Status> getBacklogStatuses() {
		return statusRepository.findByCategory(CATEGORY);
	}
	
}
