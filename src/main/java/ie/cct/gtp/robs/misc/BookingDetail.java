package ie.cct.gtp.robs.misc;

import java.math.BigDecimal;
import java.util.List;

public class BookingDetail {

	private String firstName;
	private String LastName;
	private String email;
	private List<BookingSlotDetail> bookingSlots;
	private BigDecimal totalAmount;
	
	public BookingDetail(String firstName, String lastName, String email, List<BookingSlotDetail> bookingSlots,
			BigDecimal totalAmount) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
		this.bookingSlots = bookingSlots;
		this.totalAmount = totalAmount;
	}
	
	public BookingDetail() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BookingSlotDetail> getBookingSlots() {
		return bookingSlots;
	}

	public void setBookingSlots(List<BookingSlotDetail> bookingSlots) {
		this.bookingSlots = bookingSlots;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "BookingDetail [firstName=" + firstName + ", LastName=" + LastName + ", email=" + email
				+ ", bookingSlots=" + bookingSlots + ", totalAmount=" + totalAmount + "]";
	}


	
	
	
}
