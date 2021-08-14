package ie.cct.gtp.robs.misc;

public class BookingSlotRequest {

	private String date;
	private int startTime;
	private int timeOffset;
	private String roomType;
	
	public BookingSlotRequest(String date, int startTime, int timeOffset, String roomType) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.timeOffset = timeOffset;
		this.roomType = roomType;
	}
	
	public BookingSlotRequest() {
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(int timeOffset) {
		this.timeOffset = timeOffset;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "BookingSlotRequest [date=" + date + ", startTime=" + startTime + ", timeOffset=" + timeOffset
				+ ", roomType=" + roomType + "]";
	}
	
	
}
