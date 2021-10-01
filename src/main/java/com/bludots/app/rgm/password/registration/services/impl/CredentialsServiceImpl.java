package com.bludots.app.rgm.password.registration.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bludots.app.rgm.password.registration.repositories.CredentialsRepository;
import com.bludots.app.rgm.password.registration.repositories.entities.Credentials;
import com.bludots.app.rgm.password.registration.services.CredentialsService;
import com.bludots.app.rgm.password.registration.valueObjects.CredentialsVO;

@Transactional
@Service
public class CredentialsServiceImpl implements CredentialsService{

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Override
	public Boolean persistCredentials(CredentialsVO vo) {
		// TODO Auto-generated method stub
		Credentials credentials = credentialsRepository.findByEmail(vo.getEmail());
		if(credentials==null) {
			credentials = new Credentials();
			credentials.setEmail(vo.getEmail());
		}
		if(vo.getPassword().equals(vo.getConfirmPassword())) {
			credentials.setPassword(vo.getPassword());
		}
		LocalDateTime timeLapsedDateTime = LocalDateTime.now();
		credentials.setActiveTillDate(timeLapsedDateTime.plusDays(90));
		credentialsRepository.save(credentials);
		return Boolean .TRUE;
	}
	

}
