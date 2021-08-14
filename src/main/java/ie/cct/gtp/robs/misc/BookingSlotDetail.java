package ie.cct.gtp.robs.misc;

import java.time.LocalDate;

import ie.cct.gtp.robs.entities.Room;

public class BookingSlotDetail {
	private LocalDate date;
	private Integer startTime;
	private Integer timeOffset;
	private String roomName;
	private Room room;
	
	public BookingSlotDetail(LocalDate date, Integer startTime, Integer timeOffset, String roomName, Room room) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.timeOffset = timeOffset;
		this.roomName = roomName;
		this.room = room;
	}
	
	public BookingSlotDetail() {
		super();
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "BookingSlotDetail [date=" + date + ", startTime=" + startTime + ", timeOffset=" + timeOffset
				+ ", roomName=" + roomName + ", room=" + room + "]";
	}
	
	
}
