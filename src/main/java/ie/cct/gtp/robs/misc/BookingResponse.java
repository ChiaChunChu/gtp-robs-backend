package ie.cct.gtp.robs.misc;

import java.math.BigDecimal;

public class BookingResponse {

	private Long bookingId;
	private String bookingSerial;
	private BigDecimal amount;
	
	public BookingResponse(Long bookingId, String bookingSerial, BigDecimal amount) {
		super();
		this.bookingId = bookingId;
		this.bookingSerial = bookingSerial;
		this.amount = amount;
	}
	
	public BookingResponse() {
		super();
	}
	
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	@Override
	public String toString() {
		return "BookingResponse [bookingId=" + bookingId + ", bookingSerial=" + bookingSerial + ", amount=" + amount
				+ "]";
	}

	
}
