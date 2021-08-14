package ie.cct.gtp.robs.misc;

import java.math.BigDecimal;

import ie.cct.gtp.robs.entities.BookingSlot;

public class BookingSlotResponse {

	private boolean isSlotValid;
	private BookingSlot bookingSlot;
	private BigDecimal amount;
		
	public BookingSlotResponse(boolean isSlotValid, BookingSlot bookingSlot, BigDecimal amount) {
		super();
		this.isSlotValid = isSlotValid;
		this.bookingSlot = bookingSlot;
		this.amount = amount;
	}

	public BookingSlotResponse() {
		super();
	}

	public boolean isSlotValid() {
		return isSlotValid;
	}

	public void setSlotValid(boolean isSlotValid) {
		this.isSlotValid = isSlotValid;
	}

	public BookingSlot getBookingSlot() {
		return bookingSlot;
	}

	public void setBookingSlot(BookingSlot bookingSlot) {
		this.bookingSlot = bookingSlot;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BookingSlotResponse [isSlotValid=" + isSlotValid + ", bookingSlot=" + bookingSlot + ", amount=" + amount
				+ "]";
	}


	
}
