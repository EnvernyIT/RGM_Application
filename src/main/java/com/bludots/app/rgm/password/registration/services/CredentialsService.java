package com.bludots.app.rgm.password.registration.services;

import com.bludots.app.rgm.password.registration.repositories.entities.Credentials;
import com.bludots.app.rgm.password.registration.valueObjects.CredentialsVO;

public interface CredentialsService {
	
//	public Credentials persistCredentials(CredentialsVO vo);
	public Boolean persistCredentials(CredentialsVO vo);

}
