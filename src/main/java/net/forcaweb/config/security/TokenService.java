package net.forcaweb.config.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import net.forcaweb.entities.User;

@Service
public class TokenService {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
							.withIssuer("blog-backend-fw")
							.withSubject(user.getEmail())
							.withExpiresAt(Instant.now().plusMillis(expiration))
							.sign(algorithm);
			return token;
		}catch(JWTCreationException exception) {
			throw new RuntimeException("Error ao criar token!", exception);
		}
						
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("blog-backend-fw")
					.build()
					.verify(token)
					.getSubject();
		}catch(JWTCreationException exception) {
			return "";
		}
	}

	public String extractUsername(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("blog-backend-fw")
					.build()
					.verify(token)
					.getSubject();
		}catch(JWTCreationException exception) {
			return "";
		}
	}
}
