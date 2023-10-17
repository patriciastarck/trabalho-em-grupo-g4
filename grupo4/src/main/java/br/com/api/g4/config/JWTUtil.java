package br.com.api.g4.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.g4.entities.User;
import io.jsonwebtoken.Jwts;

public class JWTUtil {

	@Value("${jwt-secret}")
	private String secret;

	@Value("${jwt-subject}")
	private String subject;

	@Value("${jwt-company-project-name}")
	private String companyProjectName;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
		return JWT.create().withSubject(subject).withClaim("email", email).withIssuedAt(new Date())
				.withIssuer(companyProjectName).sign(Algorithm.HMAC256(secret));
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String generateTokenWithUserData(User user) throws IllegalArgumentException, JWTCreationException {
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return JWT.create().withSubject(subject).withClaim("usuario", userJson).withIssuedAt(new Date())
				.withIssuer(companyProjectName).sign(Algorithm.HMAC256(secret));
	}

	public String validateTokenAndRetrieveSubject(String token) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withSubject(subject)
				.withIssuer(companyProjectName).build();
		DecodedJWT jwt = verifier.verify(token);
		User user = new User();
		try {
			user = mapper.readValue(jwt.getClaim("usuario").asString(), User.class);
		} catch (JsonProcessingException e) {
			throw new Exception("Ocorreu um erro e nao foi possivel converter o usario a partir da string json - " + e);
		}
		return user.getEmail();
	}
}
