package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.WorkflowStatus;
import com.jhallat.simple.kanban.repository.WorkflowStatusRepository;

@RestController
@RequestMapping("api/v1")
public class WorkflowStatusController {

	@Autowired
	private WorkflowStatusRepository workflowStatusRepository;
	
	@GetMapping("workflow-statuses")
	public List<WorkflowStatus> getWorkflowStatuses() {
		return workflowStatusRepository.findAll();
	}
	
}
