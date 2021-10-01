package com.bludots.app.rgm.password.registration.valueObjects;

import java.time.LocalDateTime;

public class CredentialsVO {
	
	private Long id;
	private String email;
	private String password;
	private String confirmPassword;
	private LocalDateTime activeTillDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getActiveTillDate() {
		return activeTillDate;
	}
	public void setActiveTillDate(LocalDateTime activeTillDate) {
		this.activeTillDate = activeTillDate;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
