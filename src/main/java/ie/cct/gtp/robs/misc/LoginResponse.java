package ie.cct.gtp.robs.misc;

public class LoginResponse {

	private Long id;
	private String firstName;
	private String token;
	private String role;
	
	public LoginResponse(Long id, String firstName, String token, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.token = token;
		this.role = role;
	}
	
	public LoginResponse() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginResponse [id=" + id + ", firstName=" + firstName + ", token=" + token + ", role=" + role + "]";
	}
	
}

