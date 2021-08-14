package ie.cct.gtp.robs.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name = "Room")
@Table(name = "room")
public class Room {

	@Id
	@SequenceGenerator(
			name = "room_sequence",
			sequenceName = "room_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "room_sequence"
	)
	@Column(
			name = "id",
			updatable = false
	)
	private Integer id;
	
	@Column(
			name = "room_type",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String roomType;
	
	@Column(
			name = "room_name",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String roomName;
	
	@Column(
			name = "price_hour",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private BigDecimal pricePerHour;
	
	@Column(
			name = "price_day",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private BigDecimal pricePerDay;
	
	public Room() {
		super();
	}
	
	public Room(String roomName, String roomType, BigDecimal pricePerHour, BigDecimal pricePerDay) {
		super();
		this.roomName = roomName;
		this.roomType = roomType;
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public BigDecimal getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(BigDecimal pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public BigDecimal getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(BigDecimal pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomType=" + roomType + ", roomName=" + roomName + ", pricePerHour=" + pricePerHour
				+ ", pricePerDay=" + pricePerDay + "]";
	}

	
	
}
