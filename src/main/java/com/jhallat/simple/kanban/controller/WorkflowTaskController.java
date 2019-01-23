package com.jhallat.simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.WorkflowTask;
import com.jhallat.simple.kanban.repository.WorkflowTaskRepository;

@RestController
@RequestMapping("api/v1")
public class WorkflowTaskController {

	@Autowired
	private WorkflowTaskRepository workflowTaskRepository;
	
	@GetMapping("workflow-tasks")
	public List<WorkflowTask> getWorkflowTasks() {
		return workflowTaskRepository.findAll();
	}
	
	//TODO Find a better way than returning the whole list
	@PutMapping("workflow-tasks/{id}")
	public List<WorkflowTask> updateWorkflowTask(@PathVariable("id") int id, @RequestBody WorkflowTask workflowTask) {
		
		//TODO Ensure that the id and workflow.id match, otherwise through an error
		workflowTaskRepository.saveAndFlush(workflowTask);
		return workflowTaskRepository.findAll();
		
	}
	
}
