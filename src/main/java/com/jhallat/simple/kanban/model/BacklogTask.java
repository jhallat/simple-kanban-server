package com.jhallat.simple.kanban.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="backlog_task")
public class BacklogTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Min(1)
	private int statusId;
	private long userId;
	private int goalId;
	@NotBlank
	private String description;
	private LocalDate creationDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return this.userId;
	}
}
