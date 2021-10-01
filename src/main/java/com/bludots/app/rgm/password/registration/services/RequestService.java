package com.bludots.app.rgm.password.registration.services;

import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.valueObjects.RequestVO;

public interface RequestService {
	
	public Request persistRequest(RequestVO vo);
	
	public Request findRequest(String token);
}

