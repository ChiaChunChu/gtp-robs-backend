package ie.cct.gtp.robs.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ie.cct.gtp.robs.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Configuration
public class JwtUtils {
	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	@Autowired
	public JwtUtils(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}
	
	public String createJwtToken(String username) {
		
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + jwtConfig.getTokenExpirationMillis();
		Date exp = new Date(expMillis);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(exp)
				.signWith(secretKey)
				.compact();
		
		return token;
	}
	
	public String getUsernameFromJwtToken(String token) {
		
		String username;
		
		username = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody().getSubject();
			
		return username;
		
	}
	
	public boolean isTokenExpired(String token) throws ExpiredJwtException {
		
		Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		
		return claim.getExpiration().before(new Date());
	}
	
	
	
	public boolean validateJwtToken(String token) throws UnauthorizedException, JwtException {
		
		Jws<Claims> jws;
		try {
			
			if (isTokenExpired(token)) {
				return false;
			}
	
			jws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
			
		} catch (ExpiredJwtException ex) {
			
			throw new UnauthorizedException("The token is expired.");

		} catch (JwtException ex) {
			
			throw new UnauthorizedException("The token is invalid.");
			
		}
	}
	
	
}
