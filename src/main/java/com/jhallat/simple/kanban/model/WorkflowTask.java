package com.jhallat.simple.kanban.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="workflow_task")
public class WorkflowTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int statusId;
	private String description;
	private LocalDate creationDate;
	private LocalDate statusDate;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(LocalDate statusDate) {
		this.statusDate = statusDate;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
}
