package com.jhallat.simple.kanban.model;

public class UserAuthorization {

	private String username;
	private String bearerToken;
	private boolean isAuthenticated;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBearerToken() {
		return bearerToken;
	}
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
}
