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

import com.jhallat.simple.kanban.model.BacklogStatus;
import com.jhallat.simple.kanban.model.BacklogTask;
import com.jhallat.simple.kanban.model.WorkflowStatus;
import com.jhallat.simple.kanban.model.WorkflowTask;
import com.jhallat.simple.kanban.repository.BacklogStatusRepository;
import com.jhallat.simple.kanban.repository.BacklogTaskRepository;
import com.jhallat.simple.kanban.repository.WorkflowStatusRepository;
import com.jhallat.simple.kanban.repository.WorkflowTaskRepository;

@RestController
@RequestMapping("api/v1")
public class BacklogTaskController {

	@Autowired
	private BacklogTaskRepository backlogTaskRepository;
	
	@Autowired
	private BacklogStatusRepository backlogStatusRepository;
	
	@Autowired
	private WorkflowTaskRepository workflowTaskRepository;
	
	@Autowired
	private WorkflowStatusRepository workflowStatusRepository;
	
	private int workflowId = 0;
	private int readyId = 0;
	
	@GetMapping("/backlog-tasks")
	public List<BacklogTask> getBacklogTasks() {
		
		return backlogTaskRepository.findAll();
		
	}
	
	@PostMapping("/backlog-tasks")
	public BacklogTask addBacklogTask(@RequestBody BacklogTask backlogTask) {
		
		return backlogTaskRepository.saveAndFlush(backlogTask);
		
	}
	
	@PutMapping("/backlog-tasks/{id}") 
	public BacklogTask updateBacklogTask(@PathVariable("id") int id, @RequestBody BacklogTask backlogTask){
		
		if (workflowId == 0) {
			Optional<BacklogStatus> returnValue = backlogStatusRepository.findByCode("workflow");
			if (returnValue.isPresent()) {
				workflowId = returnValue.get().getId();
			}
		}
		
		if (readyId == 0) {
			Optional<WorkflowStatus> returnValue = workflowStatusRepository.findByCode("ready");
			if (returnValue.isPresent()) {
				readyId = returnValue.get().getId();
			}
		}
		
		//TODO Return an error if path id and task id do not match. Id is immutable.
		Optional<BacklogTask> searchResult = backlogTaskRepository.findById(id);
		if (searchResult.isPresent()) {
			BacklogTask currentTask = searchResult.get();
			currentTask.setId(backlogTask.getId());
			currentTask.setStatusId(backlogTask.getStatusId());
			currentTask.setDescription(backlogTask.getDescription());
			if (backlogTask.getStatusId() == workflowId) {
				WorkflowTask workflowTask = new WorkflowTask();
				workflowTask.setDescription(backlogTask.getDescription());
				workflowTask.setStatusId(readyId);
				workflowTaskRepository.saveAndFlush(workflowTask);
			}
			return backlogTaskRepository.saveAndFlush(currentTask);
		} else {
			//TODO This should return an error
			return addBacklogTask(backlogTask);
		}
		
		
	}
}
