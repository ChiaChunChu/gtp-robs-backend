package ie.cct.gtp.robs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gtp.robs.constants.UserRole;
import ie.cct.gtp.robs.entities.User;
import ie.cct.gtp.robs.exception.BadRequestException;
import ie.cct.gtp.robs.misc.LoginResponse;
import ie.cct.gtp.robs.misc.RegistrationRequest;

@Service
public class RegistrationService {

	private final UserService userService;
	private final EmailValidator emailVaolidator;
	
	@Autowired
	public RegistrationService(UserService userService, EmailValidator emailVaolidator) throws Exception {
		this.userService = userService;
		this.emailVaolidator = emailVaolidator;
	}
	
	public LoginResponse register(RegistrationRequest request) {
		
		boolean isValidEmail = emailVaolidator.test(request.getEmail());
		
		if (!isValidEmail) {
			System.out.println("Email address is invalid.");
			throw new BadRequestException("Email address is invalid.");
		}

		LoginResponse loginResponse;
		loginResponse = userService.userSignup(
				new User(
						request.getFirstName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						request.getPhone(),
						UserRole.CUSTOMER.toString()));

		return loginResponse;
	}
	
}
