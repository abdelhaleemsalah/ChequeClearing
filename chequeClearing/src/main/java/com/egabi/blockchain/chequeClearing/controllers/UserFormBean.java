package com.egabi.blockchain.chequeClearing.controllers;

public class UserFormBean 
{
	private String username;
	private String password;
	private String userRole;
	private String userNationalId;
	private boolean isEnabled;
	private Long userId;
	
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserNationalId() {
		return userNationalId;
	}
	public void setUserNationalId(String userNationalId) {
		this.userNationalId = userNationalId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
