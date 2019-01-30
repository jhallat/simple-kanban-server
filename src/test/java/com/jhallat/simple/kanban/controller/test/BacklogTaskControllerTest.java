package com.jhallat.simple.kanban.controller.test;

import static org.hamcrest.CoreMatchers.equalTo;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.jhallat.simple.kanban.controller.BacklogTaskController;
import com.jhallat.simple.kanban.model.BacklogTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BacklogTaskControllerTest {

	@Autowired
	BacklogTaskController backlogTaskController;
	
	
	@Test
	public void contextLoads() {
		Assert.assertNotNull(backlogTaskController);
	}

	
	@Test
	public void testCRUDOperations() {
		
		final String DESCRIPTION = "Test Backlog Task";
		final int STATUS_ID = 1;
		
		RestTemplate restTemplate = new RestTemplate();
		//TODO Externalize URL??
		String postURL = "http://localhost:8080/backlog-tasks";
		
		BacklogTask backlogTask = new BacklogTask();
		backlogTask.setId(0);
		backlogTask.setDescription(DESCRIPTION);
		backlogTask.setStatusId(STATUS_ID);
		
		ResponseEntity<BacklogTask> response = restTemplate.postForEntity(postURL, backlogTask, BacklogTask.class);
		BacklogTask postTask = response.getBody();
		Assertions.assertThat(postTask.getId()).isGreaterThan(0);
		
		String getURL = String.format("http://localhost:8080/backlog-tasks/%s", postTask.getId());
		BacklogTask getTask = restTemplate.getForObject(getURL, BacklogTask.class);
		//Assertions.assertThat(getTask.getDescription(), equalTo(DESCRIPTION));
		
		
	}
	
}
