package com.bludots.app.rgm.password.registration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bludots.app.rgm.password.registration.repositories.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {

	public Request findByEmail(String email);
	
	public Request findByToken(String token);
	 
	@Query(
			value = "SELECT TOKEN_ FROM REQUESTS_ r WHERE r.TOKEN_ = ?1",
			nativeQuery = true)
	public List<String> requestList();
}
