package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.WorkflowTask;
import com.jhallat.simple.kanban.repository.WorkflowTaskRepository;
import com.jhallat.simple.kanban.security.CurrentUserUtility;

@RestController
@RequestMapping("api/v1")
public class WorkflowTaskController {

	@Autowired
	private CurrentUserUtility currentUserUtility;
	
	@Autowired
	private WorkflowTaskRepository workflowTaskRepository;
	
	@GetMapping("workflow-tasks")
	public List<WorkflowTask> getWorkflowTasks(@RequestHeader HttpHeaders headers) {
		long userId = currentUserUtility.getCurrentUserId(headers);
		return workflowTaskRepository.findByUserId(userId);
	}
	
	//TODO Find a better way than returning the whole list
	@PutMapping("workflow-tasks/{id}")
	public List<WorkflowTask> updateWorkflowTask(@PathVariable("id") int id, @RequestBody WorkflowTask workflowTask, @RequestHeader HttpHeaders headers) {
		long userId = currentUserUtility.getCurrentUserId(headers);
		//TODO Ensure that the id and workflow.id match, otherwise through an error
		workflowTask.setUserId(userId);
		workflowTaskRepository.saveAndFlush(workflowTask);
		return workflowTaskRepository.findAll();
		
	}
	
}
