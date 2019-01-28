package com.jhallat.simple.kanban.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="goal")
public class Goal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int statusId;
	private String description;
	private String expectation;
	private String alternatives;
	private String strategy;
	
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
	public String getExpectation() {
		return expectation;
	}
	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}
	public String getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(String alternatives) {
		this.alternatives = alternatives;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	

}
