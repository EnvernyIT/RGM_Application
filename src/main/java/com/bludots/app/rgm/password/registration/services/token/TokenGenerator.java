package com.bludots.app.rgm.password.registration.services.token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenGenerator {
	
	public String generateToken() {
		Instant now = Instant.now();
		byte[] secret = Base64.getDecoder().decode("WNi3oF3NfduzvwUiOPlnDdUUjIlMcv7fX28ms3udpPM=");

		String jwt = Jwts.builder().setSubject("rgmpwdreg").setAudience("workers")
				.claim("1d20", new Random().nextInt(10) + 1).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(1, ChronoUnit.MINUTES))).signWith(Keys.hmacShaKeyFor(secret))
				.compact();

		System.out.println(jwt);

		Jws<Claims> result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret)).parseClaimsJws(jwt);
		System.out.println(result.toString());
		
		return jwt;

	}

}
