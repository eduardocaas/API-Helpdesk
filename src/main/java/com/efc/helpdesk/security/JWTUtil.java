package com.efc.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(String email) { 
		
		return Jwts.builder()
					.setSubject(email)
					.setExpiration(new Date(System.currentTimeMillis() + expiration)) // Horário atual + expiração
					.signWith(SignatureAlgorithm.HS512, secret.getBytes()) // Algoritimo de encriptação + Segredo (String para bytes)
					.compact(); // Compactação -> performance
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token); // Claims -> informações da entidade
		if(claims != null) {
			String username = claims.getSubject(); // Subject - campos do usuário
			Date expirationDate = claims.getExpiration(); // Campo de expiração
			Date now = new Date(System.currentTimeMillis());
			
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody(); // Pega o corpo do token
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject(); // Retorna o subject do usuário
		}
		return null;
	}
	
}
