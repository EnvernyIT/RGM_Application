package com.bludots.app.rgm.password.registration.repositories.entities;

import java.time.LocalDateTime;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "REQUESTS_")
public class Request {

	@Id
	@TableGenerator(name = "table", 
	table = "sequences_", 
	pkColumnName = "PK_NAME", 
	valueColumnName = "PK_VALUE", 
	initialValue = 0,
	allocationSize = 1)
	@GeneratedValue(generator = "table", 
	strategy = GenerationType.TABLE)
	@Column(name = "ID_")
	private Long id;

	@Column(name = "EMAIL_")
	private String email;

	@Column(name = "TOKEN_")
	private String token;

	@Column(name = "REQUEST_DATE_TIME_")
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, requestDateTime, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(requestDateTime, other.requestDateTime) && Objects.equals(token, other.token);
	}

	
	
}
