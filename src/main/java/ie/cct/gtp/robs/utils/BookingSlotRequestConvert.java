package ie.cct.gtp.robs.utils;

import java.time.LocalDate;

import ie.cct.gtp.robs.entities.BookingSlot;
import ie.cct.gtp.robs.misc.BookingSlotRequest;

public class BookingSlotRequestConvert {

	public static BookingSlot RequestToBookingSlot(BookingSlotRequest request, String roomName) {
		
		BookingSlot slot = new BookingSlot();
		LocalDate date = LocalDateUtils.StrToLocalDate(request.getDate());
		
		slot.setDate(date);
		slot.setStartTime(request.getStartTime());
		slot.setTimeOffset(request.getTimeOffset());
		slot.setRoomName(roomName);
		
		return slot;
	}
	
}
