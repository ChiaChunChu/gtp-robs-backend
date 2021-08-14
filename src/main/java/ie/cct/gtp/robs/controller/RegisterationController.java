package ie.cct.gtp.robs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gtp.robs.misc.LoginResponse;
import ie.cct.gtp.robs.misc.RegistrationRequest;
import ie.cct.gtp.robs.service.RegistrationService;

@RestController
@RequestMapping("api/registration")
@CrossOrigin("*")
public class RegisterationController {
	
	private final RegistrationService registrationService;
	
	@Autowired
	public RegisterationController (RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping
	public LoginResponse register(
			@RequestBody(required = true) RegistrationRequest request) throws Exception {
		return registrationService.register(request);
	}
	
}
