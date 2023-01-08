package com.efc.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("{jwt.expiration}")
	private Long expiration;
	
	@Value("{jwt.secret}")
	private String secret;
	
	public String generateToken(String email) { 
		
		return Jwts.builder()
					.setSubject(email)
					.setExpiration(new Date(System.currentTimeMillis() + expiration)) // Horário atual + expiração
					.signWith(SignatureAlgorithm.HS512, secret.getBytes()) // Algoritimo de encriptação + Segredo (String para bytes)
					.compact(); // Compactação -> performance
	}
	
}
