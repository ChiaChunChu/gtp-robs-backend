package ie.cct.gtp.robs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gtp.robs.exception.UnauthorizedException;
import ie.cct.gtp.robs.jwt.JwtConfig;
import ie.cct.gtp.robs.jwt.JwtUtils;
import io.jsonwebtoken.JwtException;

@Service
public class AuthorizationConfirmer {

	private final JwtConfig jwtConfig;
	private final JwtUtils jwtUtils;
	
	@Autowired
	public AuthorizationConfirmer (
			JwtConfig jwtConfig,
			JwtUtils jwtUtils) {
		this.jwtConfig = jwtConfig;
		this.jwtUtils = jwtUtils;
	}
	
	public boolean isTokenValid(String authorizationHeader) throws UnauthorizedException, JwtException {
		
		//remove the prefix "Bearer "
		String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "").replace(" ", "");

		// check token
		// if return false, it throws an exception
		if (!jwtUtils.validateJwtToken(token)) {
			return false;
		}	
		
		return true;
	}
}
