package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.BacklogStatus;
import com.jhallat.simple.kanban.repository.BacklogStatusRepository;

@RestController
@RequestMapping("api/v1")
public class BacklogStatusController {

	@Autowired
	private BacklogStatusRepository backlogStatusRepository;
	
	@GetMapping("backlog-statuses")
	public List<BacklogStatus> getBacklogStatuses() {
		return backlogStatusRepository.findAll();
	}
	
}
