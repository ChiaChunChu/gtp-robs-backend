package ie.cct.gtp.robs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ie.cct.gtp.robs.dao.UserRepository;
import ie.cct.gtp.robs.entities.User;
import ie.cct.gtp.robs.exception.BadRequestException;
import ie.cct.gtp.robs.exception.UnauthorizedException;
import ie.cct.gtp.robs.jwt.JwtUtils;
import ie.cct.gtp.robs.misc.LoginResponse;
import ie.cct.gtp.robs.misc.ProfileResponse;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtUtils jwtUtils;

	@Autowired
	public UserService(
			UserRepository userRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder,
			JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtUtils = jwtUtils;
	}
	
	
	public User insertUser(User user) {
		return userRepository.saveAndFlush(user);
	}
	
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
    public boolean isUserAccountExist(String email) {
    	return userRepository.findByEmail(email).isPresent();
    }

    public User getUserById(Long id) {
    	User user = userRepository.findById(id).get();
    	return user;
    }
    
    public User getUserByEmail(String email) {
    	User user = userRepository.findByEmail(email).get();
    	return user;
    }
    
    public Long getUserIdByEmail(String email) {
    	User user = userRepository.findByEmail(email).get();
    	return user.getId();
    }
    
    public String getUserFirstNameByEmail(String email) {
    	User user = userRepository.findByEmail(email).get();
    	return user.getFirstname();
    }
    
    public ProfileResponse getUserInfo(Long id){
    	
        User user = getUserById(id);

    	return new ProfileResponse(
    			user.getFirstname(),
    			user.getLastname(),
    			user.getEmail(),
    			user.getPhone(),
    			user.getRole().toString());
    }
    
	public LoginResponse userSignup(User user) {
		
		boolean isEmailExist = userRepository.findByEmail(user.getEmail()).isPresent();
		
		if (isEmailExist) {
			System.out.println("isEmailExist");
			throw new BadRequestException("Email: " + user.getEmail() + " has already been registered.");
		}

		// Encrypt the password
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		//debug
		System.out.println(encodedPassword);
		user.setPassword(encodedPassword);
		User savedUser = insertUser(user);
		
		// Create token
		String token = jwtUtils.createJwtToken(user.getEmail());
		
		return new LoginResponse(savedUser.getId(), savedUser.getFirstname(), token, savedUser.getRole());
	}

	public LoginResponse userLogin(String email, String password) {
		
		// check the email
		if (!isUserAccountExist(email)) {
			// user not exist, go registration
			throw new UnauthorizedException("This email has not been registered.");
		}
		// check the password
		User user = getUserByEmail(email);
		if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
			throw new UnauthorizedException("The password is not match.");
		}
	
		User savedUser = insertUser(user);
		
		// Create token
		String token = jwtUtils.createJwtToken(savedUser.getEmail());
		
		return new LoginResponse(savedUser.getId(), savedUser.getFirstname(), token, savedUser.getRole());
	}
	
	
	
}
