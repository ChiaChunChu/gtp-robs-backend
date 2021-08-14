package ie.cct.gtp.robs.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "Booking")
@Table(name = "booking_list")
public class Booking {

	@Id
	@SequenceGenerator(
			name = "booking_sequence",
			sequenceName = "booking_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "booking_sequence"
	)
	@Column(
			name = "id",
			updatable = false
	)
	private Long id;

	@Column(
			name = "booking_serial",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String bookingSerial;
	
	@Column(
			name = "amount",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private BigDecimal amount;

	/**
	 * Many bookings can belong to one user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name = "user_id",
			nullable = false,
			referencedColumnName = "id",
			foreignKey = @ForeignKey(
					name = "user_booking_fk"
			)
	)
	@JsonBackReference("bookings")
	private User user;
	
	@OneToMany(
			mappedBy = "booking",
			orphanRemoval = true,
			cascade = CascadeType.ALL
	)
	private List<BookingSlot> bookingSlots = new ArrayList<>();
	
	public Booking() {
		super();
	}

	public Booking(String bookingSerial, BigDecimal amount, User user) {
		super();
		this.bookingSerial = bookingSerial;
		this.amount = amount;
		this.user = user;
	}
	
	public Booking(String bookingSerial, BigDecimal amount) {
		super();
		this.bookingSerial = bookingSerial;
		this.amount = amount;
	}
	
	public Booking(Long id, String bookingSerial, BigDecimal amount, User user) {
		super();
		this.id = id;
		this.bookingSerial = bookingSerial;
		this.amount = amount;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBookingSerial() {
		return bookingSerial;
	}

	public void setBookingSerial(String bookingSerial) {
		this.bookingSerial = bookingSerial;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Add new booking slot in a booking
	 * @param bookingSlot
	 */
	public void addBookingSlot(BookingSlot bookingSlot) {
		if (!this.bookingSlots.contains(bookingSlot)) {
			this.bookingSlots.add(bookingSlot);
			bookingSlot.setBooking(this);
		}
	}
	
	/**
	 * Remove a booking slot from a booking
	 * @param bookingSlot
	 */
	public void removeBookingSlot(BookingSlot bookingSlot) {
		if (this.bookingSlots.contains(bookingSlot)) {
			this.bookingSlots.remove(bookingSlot);
			bookingSlot.setBooking(null);
		}
	}

//	@Override
//	public String toString() {
//		return "Booking [id=" + id + ", amount=" + amount + ", user=" + user + "]";
//	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingSerial=" + bookingSerial + ", amount=" + amount + ", user=" + user + "]";
	}
	

	
}
