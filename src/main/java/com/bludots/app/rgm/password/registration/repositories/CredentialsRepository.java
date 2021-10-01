package com.bludots.app.rgm.password.registration.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bludots.app.rgm.password.registration.repositories.entities.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long>{
	
	public Credentials findByEmail(String email);

}
