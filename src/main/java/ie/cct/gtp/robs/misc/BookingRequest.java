package ie.cct.gtp.robs.misc;

public class BookingRequest {

	private String date;
	private int startTime;
	private int timeOffset;
	private String roomName;
	
	public BookingRequest(String date, int startTime, int timeOffset, String roomName) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.timeOffset = timeOffset;
		this.roomName = roomName;
	}

	public BookingRequest() {
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
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	
	@Override
	public String toString() {
		return "BookingRequest [date=" + date + ", startTime=" + startTime + ", timeOffset=" + timeOffset
				+ ", roomName=" + roomName + "]";
	}
	
}
