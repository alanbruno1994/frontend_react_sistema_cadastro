package com.registrationsystem.workshop.config.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.registrationsystem.workshop.entities.UserSystem;
import com.registrationsystem.workshop.resources.exceptions.InvalidToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 1000;// 1 minutos
	//public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;// 24 horas

	@Value("${jwt.secret}")
	private String secret;

	// retorna o username do token jwt
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retorna expiration date do token jwt
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	// para retornar qualquer informação do token nos iremos precisar da secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Cria o token e define tempo de expiração pra ele
	public String doGenerateToken(UserSystem user) {
		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setSubject(user.getName_user())// Aqui e
																												// que
																												// criou
																												// o
																												// token
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	// valida o token
	public Boolean validateToken(String token) {
		try {			
			Claims claims = decodeToken(token);
			System.out.println(claims.getIssuer());
			System.out.println(claims.getIssuedAt());
			// Verifica se o token está expirado
			if (claims.getExpiration().before(new Date(System.currentTimeMillis())))
				throw new Exception();
			
			System.out.println(claims.getExpiration());
			return true;

		} catch (Exception e) {

		}
		return false;
	}
	
	
	   public Claims decodeToken(String token) {
	        return Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody();
	    }
	   
	   public void authorize(String Authorization)
	   {
		   if (Authorization != null) {
				if (validateToken(Authorization.replaceAll("Bearer", "").replaceAll(" ", "")) == false) {
					throw new InvalidToken("O token não está válido!");
				}
			} else {
				throw new InvalidToken("O token não está válido");
			}
	   }

}
