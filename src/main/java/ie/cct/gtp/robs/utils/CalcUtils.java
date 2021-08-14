package ie.cct.gtp.robs.utils;

import java.math.BigDecimal;

import ie.cct.gtp.robs.constants.Constant;
import ie.cct.gtp.robs.entities.BookingSlot;
import ie.cct.gtp.robs.entities.Room;
import ie.cct.gtp.robs.misc.BookingRequest;

public class CalcUtils {

	public static final BigDecimal amountCalc(BookingRequest request, Room room) {
		
		BigDecimal amount;

		if (request.getTimeOffset() == Constant.DAY_TIME_OFFSET) { // the booking is a day
			amount = room.getPricePerDay();
		} else {
			amount = room.getPricePerHour().multiply(new BigDecimal(request.getTimeOffset()));
		}
		
		return amount;
	}
	
	public static final BigDecimal slotAmountCalc(BookingSlot slot, Room room) {
		
		BigDecimal amount;

		if (slot.getTimeOffset() == Constant.DAY_TIME_OFFSET) { // the booking is a day
			amount = room.getPricePerDay();
		} else {
			amount = room.getPricePerHour().multiply(new BigDecimal(slot.getTimeOffset()));
		}
		
		return amount;
	}
	
	
	
}
