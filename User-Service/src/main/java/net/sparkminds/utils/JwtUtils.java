package net.sparkminds.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sparkminds.entity.User;

@Component
public class JwtUtils {
	
	private static String secret = "secret";
	private static long expireDuration = 60 * 60;

	public String generateJwt(User user) {
		
		long millitime = System.currentTimeMillis();
		long expireTime = millitime + expireDuration * 1000;
		
		Date issueAt = new Date(millitime);
		Date expireAt = new Date(expireTime);
		
		Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issueAt).setExpiration(expireAt);
		
		claims.put("firstname", user.getFirstname());
		claims.put("lastname", user.getLastname());
		claims.put("emailId", user.getEmailId());
		claims.put("phoneNumber", user.getPhoneNumber());
		claims.put("address", user.getAddress());
		
		return Jwts
				.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

	}
}
