package ie.cct.gtp.robs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gtp.robs.exception.UnauthorizedException;
import ie.cct.gtp.robs.misc.LoginRequest;
import ie.cct.gtp.robs.misc.LoginResponse;
import ie.cct.gtp.robs.misc.ProfileResponse;
import ie.cct.gtp.robs.security.AuthorizationConfirmer;
import ie.cct.gtp.robs.service.UserService;
import io.jsonwebtoken.JwtException;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {
	
	private final AuthorizationConfirmer authConfirmer;
	private final UserService userService;
	
	@Autowired
	public UserController(
			AuthorizationConfirmer authConfirmer,
			UserService userService) {
		this.authConfirmer = authConfirmer;
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public LoginResponse login(
			@RequestBody(required = true) LoginRequest request) throws Exception {

		return userService.userLogin(request.getEmail(), request.getPassword());
		
	}

	@GetMapping("/{userid}/info")
	public ProfileResponse getUserInfo (
			@PathVariable("userid") Long userId,
			@RequestHeader(name = "Authorization", required = true) String authorizationHeader
			) throws UnauthorizedException, JwtException {
	
		authConfirmer.isTokenValid(authorizationHeader);
		
		return userService.getUserInfo(userId);
	}
	
}
