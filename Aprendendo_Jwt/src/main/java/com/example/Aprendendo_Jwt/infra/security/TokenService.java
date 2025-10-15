package com.example.Aprendendo_Jwt.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.Aprendendo_Jwt.model.Usuario;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secrect;

	public String generateToken(Usuario usuario) {
		try {
			Algorithm algoritomo = Algorithm.HMAC256(secrect);
			String token = JWT.create().withIssuer("Aprendendo_Jwt").withSubject(usuario.getEmail())
					.withExpiresAt(this.generateExpirationDate()).sign(algoritomo);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro de autenticacao");
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm algoritomo = Algorithm.HMAC256(secrect);
			return JWT.require(algoritomo).withIssuer("Aprendendo_Jwt").build().verify(token).getSubject();

		} catch (JWTVerificationException e) {
			return null;
		}
	}

	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
	}

}
