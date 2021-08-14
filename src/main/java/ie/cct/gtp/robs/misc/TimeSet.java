package ie.cct.gtp.robs.misc;

public class TimeSet {

	private String date;
	private Integer startTime;
	private Integer timeOffset;

	public TimeSet(String date, Integer startTime, Integer timeOffset) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.timeOffset = timeOffset;
	}
	
	public TimeSet() {
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	@Override
	public String toString() {
		return "TimeSet [date=" + date + ", startTime=" + startTime + ", timeOffset=" + timeOffset + "]";
	}
	
	
	
	
	
}
