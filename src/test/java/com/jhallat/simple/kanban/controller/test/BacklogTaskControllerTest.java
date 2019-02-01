package com.jhallat.simple.kanban.controller.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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

	//TODO This test only works if service is running.
	//TODO Make system tests separate from unit tests
	@Test
	public void testCRUDOperations() {
		
		final String DESCRIPTION = "Test Backlog Task";
		final int STATUS_ID = 1;
		
		RestTemplate restTemplate = new RestTemplate();
		//TODO Externalize URL??
		String postURL = "http://localhost:8080/api/v1/backlog-tasks";
		
		BacklogTask backlogTask = new BacklogTask();
		backlogTask.setId(0);
		backlogTask.setDescription(DESCRIPTION);
		backlogTask.setStatusId(STATUS_ID);
		
		ResponseEntity<BacklogTask> response = restTemplate.postForEntity(postURL, backlogTask, BacklogTask.class);
		BacklogTask postTask = response.getBody();
		assertThat(postTask.getId(), greaterThan(0));
		
		String getURL = String.format("http://localhost:8080/api/v1/backlog-tasks", postTask.getId());
		ResponseEntity<List<BacklogTask>> getResponse = restTemplate.exchange(
				getURL,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<BacklogTask>>() {});
		List<BacklogTask> getTasks = getResponse.getBody();
		
		assertThat(getTasks, hasItem(hasProperty("description", equalTo(DESCRIPTION))));
		assertThat(getTasks, hasItem(hasProperty("id", equalTo(postTask.getId()))));
		
		String deleteURL = String.format("http://localhost:8080/api/v1/backlog-task/%s", postTask.getId());
		restTemplate.delete(deleteURL);


		
	}
	
}
