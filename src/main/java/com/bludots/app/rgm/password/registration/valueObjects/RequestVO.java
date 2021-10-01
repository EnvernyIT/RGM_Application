package com.bludots.app.rgm.password.registration.valueObjects;

import java.time.LocalDateTime;

public class RequestVO {
	
	private Long id;
	private String email;
	private LocalDateTime requestDateTime;
	
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
	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	
	
}
