package com.bludots.app.rgm.password.registration.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bludots.app.rgm.password.registration.repositories.RequestRepository;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.EmailService;
import com.bludots.app.rgm.password.registration.services.RequestService;
import com.bludots.app.rgm.password.registration.services.token.TokenGenerator;
import com.bludots.app.rgm.password.registration.valueObjects.RequestVO;

@Transactional
@Service
@EnableAsync
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;

	private TokenGenerator tokenGenerator = new TokenGenerator();
	
	private EmailService sender;
	
	public RequestServiceImpl(EmailService sender) {
		super();
		this.sender = sender;
	}

	@Override
	public Request persistRequest(RequestVO vo) {
		Request request = requestRepository.findByEmail(vo.getEmail());

		if (request == null) {
			request = new Request();
			request.setEmail(vo.getEmail());
		}

		request.setRequestDateTime(LocalDateTime.now());
		
		String token = tokenGenerator.generateToken();
		request.setToken(token);
		
		//Send mail
		String link = "http://localhost:9800/rgm.pwd.reg/registration?token=" + token;
		sender.send(
				request.getEmail(), 
				buildEmail(request.getEmail(), link));

		return requestRepository.save(request);
	}
	
	public Request findRequest(String token) {
		Request request = requestRepository.findByToken(token);
		return request;
	}
	
	public String buildEmail(String email, String link) {
		return "<table role=\"presentation\" style=\"width:602px;border-collapse:collapse;border:1px solid #cccccc;border-spacing:0;text-align:left; margin: 5px 10px 10px 10px; padding: 15px;\">\r\n"
				+ "    <tr>\r\n"
				+ "        <td align=\"center\" style=\"padding:40px 0 30px 0;background:white; margin-bottom: 10px; \">\r\n"
				+ "            <img src=\"https://rosebelgoldmines.sr/media/logo.jpg\" alt=\"\" width=\"600\" style=\"height:auto;display:block;\" />\r\n"
				+ "        </td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td style=\"padding:0; margin-bottom: 10px;\">\r\n"
				+ "          <h1>Email Registration Token</h1>\r\n"
				+ "          <p>This link is provided to register a given email with password. This request was made by "+email+" </p>\r\n"
				+ "          <p><a href="+link+">Your Link To Register</a></p>        </td>\r\n"
				+ "    </tr>\r\n"
				+ "\r\n"
				+ "</table>";
	}


}
