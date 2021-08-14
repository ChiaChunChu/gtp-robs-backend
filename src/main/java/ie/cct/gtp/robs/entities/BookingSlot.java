package ie.cct.gtp.robs.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "BookingSlot")
@Table(name = "booking_slot_list")
public class BookingSlot {

	@Id
	@SequenceGenerator(
			name = "booking_slot_sequence",
			sequenceName = "booking_slot_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "booking_slot_sequence"
	)
	@Column(
			name = "id",
			updatable = false
	)
	private Long id;

	
	@Column(
			name = "date",
			nullable = false
	)
	private LocalDate date;
	
	@Column(
			name = "start_time",
			nullable = false
	)
	private Integer startTime;
	
	@Column(
			name = "time_offset",
			nullable = false
	)
	private Integer timeOffset;

	@Column(
			name = "room_name",
			nullable = false
	)
	private String roomName;
	
	@ManyToOne(
			fetch = FetchType.EAGER
	)
	@JoinColumn(
			name = "booking_id",
			nullable = false,
			referencedColumnName = "id",
			foreignKey = @ForeignKey(
					name = "booking_slot_fk"
			)
	)
	private Booking booking;
	
	public BookingSlot() {
		super();
	}
	
	public BookingSlot(LocalDate date, Integer startTime, Integer timeOffset, String roomName) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.timeOffset = timeOffset;
		this.roomName = roomName;
	}
	
	public BookingSlot(Booking booking, LocalDate date, Integer startTime, Integer timeOffset, String roomName) {
		super();
		this.booking = booking;
		this.date = date;
		this.startTime = startTime;
		this.timeOffset = timeOffset;
		this.roomName = roomName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(Integer timeOffset) {
		this.timeOffset = timeOffset;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "BookingSlot [id=" + id + ", date=" + date + ", startTime=" + startTime + ", timeOffset=" + timeOffset
				+ ", roomName=" + roomName + ", booking=" + booking + "]";
	}



	
	
}
