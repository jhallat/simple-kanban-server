package com.jhallat.simple.kanban.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.exception.NotFoundException;
import com.jhallat.simple.kanban.model.BacklogTask;
import com.jhallat.simple.kanban.model.Status;
import com.jhallat.simple.kanban.model.WorkflowTask;
import com.jhallat.simple.kanban.repository.BacklogTaskRepository;
import com.jhallat.simple.kanban.repository.StatusRepository;
import com.jhallat.simple.kanban.repository.WorkflowTaskRepository;

@RestController
@RequestMapping("api/v1")
public class BacklogTaskController {

	private static final String BACKLOG = "backlog";
	private static final String WORKFLOW = "workflow";
	
	@Autowired
	private BacklogTaskRepository backlogTaskRepository;
	
	@Autowired
	private WorkflowTaskRepository workflowTaskRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	private int workflowId = 0;
	private int readyId = 0;

	
	@GetMapping("/backlog-tasks")
	public ResponseEntity<List<BacklogTask>> getBacklogTasks() throws NotFoundException {
	
		List<BacklogTask> backlogTasks = backlogTaskRepository.findAll();
		if (backlogTasks.isEmpty()) {
			throw new NotFoundException("No backlog tasks found");
		} 
		return new ResponseEntity<>(backlogTasks, HttpStatus.OK);
		
	}
	
	@PostMapping("/backlog-tasks")
	public BacklogTask addBacklogTask(@Valid @RequestBody BacklogTask backlogTask)  {
		
		if (backlogTask.getId() != 0) {
			throw new ValidationException("Id for a new backlog task must be unassigned");
		}
		
		return backlogTaskRepository.saveAndFlush(backlogTask);
		
	}
	
	@PutMapping("/backlog-tasks/{id}") 
	public BacklogTask updateBacklogTask(@PathVariable("id") int id, @RequestBody BacklogTask backlogTask){
		
		if (workflowId == 0) {
			Optional<Status> returnValue = statusRepository.findByCategoryAndCode(BACKLOG, "workflow");
			if (returnValue.isPresent()) {
				workflowId = returnValue.get().getId();
			}
		}
		
		if (readyId == 0) {
			Optional<Status> returnValue = statusRepository.findByCategoryAndCode(WORKFLOW, "ready");
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
	
	@DeleteMapping("/backlog-task/{id}")
	public ResponseEntity<BacklogTask> deleteBacklogTask(@PathVariable("id") int id) throws NotFoundException {
		//TODO Check to see if item exists and return a not found error if it does not
		Optional<BacklogTask> returnValue = backlogTaskRepository.findById(id);
		if (returnValue.isPresent()) {
			BacklogTask backlogTask = returnValue.get();
			backlogTaskRepository.delete(backlogTask);
			return new ResponseEntity<>(backlogTask, HttpStatus.OK);
		}
		throw new NotFoundException(String.format("Backlog Task id = %s not found.", id));
	}
}
