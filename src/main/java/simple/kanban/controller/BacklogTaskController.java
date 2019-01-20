package simple.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import simple.kanban.model.BacklogTask;
import simple.kanban.repository.BacklogTaskRepository;

@RestController
public class BacklogTaskController {

	@Autowired
	private BacklogTaskRepository backlogTaskRepository;
	
	@GetMapping("/backlogTasks")
	public List<BacklogTask> getBacklogTasks() {
		
		return backlogTaskRepository.findAll();
		
	}
	
}
