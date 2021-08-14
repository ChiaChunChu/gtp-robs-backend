package ie.cct.gtp.robs.misc;

public class ProfileResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String role;
	
	public ProfileResponse(String firstName, String lastName, String email, String phone, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}

    public ProfileResponse() {
    	super();
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ProfileResponse [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + ", role=" + role + "]";
	}
    
    
}
