package com.jhallat.simple.kanban.controller.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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
		assertThat(backlogTaskController, notNullValue());
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
		assertThat(postTask.getId(), greaterThan(0));
		
		String getURL = String.format("http://localhost:8080/backlog-tasks/%s", postTask.getId());
		BacklogTask getTask = restTemplate.getForObject(getURL, BacklogTask.class);
		assertThat(getTask.getDescription(), equalTo(DESCRIPTION));
		
		
	}
	
}
