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
@Table(name = "CREDENTIALS_")
public class Credentials {
	
	@Id
	@TableGenerator(name = "table",
	table = "sequences_",
	pkColumnName = "PK_NAME",
	valueColumnName = "PK_VALUE",
	initialValue = 0,
	allocationSize = 1)
	@GeneratedValue(generator = "table", 
	strategy = GenerationType.TABLE)
	@Column(name ="ID_")
	private Long id;
	
	@Column(name = "EMAIL_")
	private String email;
	
	@Column(name = "PASSWORD_")
	private String password;
	
	@Column(name = "ACTIVE_TILL_DATE_")
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

	@Override
	public int hashCode() {
		return Objects.hash(activeTillDate, email, id, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		return Objects.equals(activeTillDate, other.activeTillDate) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password);
	}
	
	

}
