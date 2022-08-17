package com.example.lessonEnglish.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtlis {
	
	private  String secretKey="truonggiang";
	private  Integer expireToken=8640000;
	public String generateToken(String username) {
		Map<String,Object> claims=new HashMap<>();
		return doGenerateToken(username,claims);
	}
	
	
	private String doGenerateToken(String username, Map<String, Object> claims) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis()+ expireToken))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
				
	}
	
	public String getUsernameByToken(String token) {
		return getAllClaimsToken(token, Claims::getSubject);
	}


	private <T> T getAllClaimsToken(String token, Function<Claims, T> claims) {
		Claims claim=getToken(token);
		return claims.apply(claim);
	}
	
	private Claims getToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}
