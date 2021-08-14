package ie.cct.gtp.robs.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "User")
@Table(
		name="user_list",
		uniqueConstraints = {
				@UniqueConstraint(name = "user_email_unique", columnNames = "email")
		}
)
public class User {

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)
	@Column(
			name = "id",
			updatable = false
	)
	private Long id;

	@Column(
			name = "first_name",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String firstname;
	
	@Column(
			name = "last_name",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String lastname;
	
	@Column(
			name = "email",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String email;
	
	@Column(
			name = "password",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String password;
		
	@Column(
			name = "phone",
			nullable = true,
			columnDefinition = "TEXT"
	)	
	private String phone;
	
	@Column(
			name = "role",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String role;

	@OneToMany(
			mappedBy = "user",
			orphanRemoval = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)
	@JsonManagedReference ("user")
	private List<Booking> bookings = new ArrayList<>();

	public User(Long id, String firstname, String lastname, String email, String password, String phone,
			String role, List<Booking> bookings) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.bookings = bookings;
	}	
	
	public User(String firstname, String lastname, String email, String password, String phone,
			String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}
	
	public User() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	/**
	 * Add new booking for user
	 * @param booking
	 */
	public void addBooking(Booking booking) {
		if (!this.bookings.contains(booking)) {
			this.bookings.add(booking);
			booking.setUser(this);
		}
	}
	
	/**
	 * Remove booking for user
	 * @param booking
	 */
	public void removeBooking(Booking booking) {
		if (this.bookings.contains(booking)) {
			this.bookings.remove(booking);
			booking.setUser(null);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", role=" + role + "]";
	}


	

	
}
