package ie.cct.gtp.robs.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ie.cct.gtp.robs.constants.Constant;
import ie.cct.gtp.robs.misc.TimeSet;

public class LocalDateUtils {

	public static LocalDate StrToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	
	public static String LocalDateToStr(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
		String date = localDate.format(formatter);
		return date;
	}
	
//	public static String AddDateStr(String date, Integer day) throws ParseException {
//		
//		//LocalDate localDate = StrToLocalDate(date);
//		SimpleDateFormat formatter = new SimpleDateFormat("d-MM-yyyy");
//		Calendar c = Calendar.getInstance();
//		c.setTime(formatter.parse(date));
//		c.add(Calendar.DAY_OF_MONTH, day);
//		
//		return formatter.format(c.getTime());
//	}

	private static boolean isOpeningtime(Integer startTime, Integer timeOffset) {	
		return (startTime + timeOffset) <= Constant.CLOSE_TIME;
	}

	public static boolean isWeekend(LocalDate date) {
		System.out.println("--debug isWeekend");
		DayOfWeek d = date.getDayOfWeek();
		boolean isWeekend = false;
		System.out.println(Constant.WEEKEND.length);
		for (int i=0; i<Constant.WEEKEND.length; i++) {
			if (d==Constant.WEEKEND[i]) {
				isWeekend = true;
			}
		}
		return isWeekend;
	}	
	
	public static boolean isHoliday(LocalDate date) {
		boolean isHoliday = false;
		for (int i=0; i<Constant.HOLIDAY.length; i++) {
			if (date.equals(Constant.HOLIDAY[i])) {
				isHoliday = true;
			}
		}
		return isHoliday;
	}
	
	
	public static String getNextValidDate(String oldDate) {
		
		LocalDate oldLocalDate = StrToLocalDate(oldDate);
		LocalDate newLocalDate;

		do {
			newLocalDate = oldLocalDate.plusDays(Constant.Date_SHIFT);
			oldLocalDate = newLocalDate;
		} while (isWeekend(newLocalDate) || isHoliday(newLocalDate));
		
		String newDate = LocalDateToStr(newLocalDate);
		
		return newDate;
	}
	
	
	public static TimeSet getNextValidTimeSet(TimeSet oldTimeSet) {
		System.out.println("--debug getNextValidTimeSet");
		TimeSet newTimeSet = new TimeSet();
		
		// is holiday or weekend?
		if(isHoliday(StrToLocalDate(oldTimeSet.getDate())) || isWeekend(StrToLocalDate(oldTimeSet.getDate()))) {
			oldTimeSet.setDate(getNextValidDate(oldTimeSet.getDate()));
		}
		
		// can it be the same day?
		boolean shiftToSameDate = isOpeningtime(oldTimeSet.getStartTime() + Constant.TIME_SLOT_SHIFT, 
				oldTimeSet.getTimeOffset());
		if (shiftToSameDate) {
			// it's OK to shift to the same day
			newTimeSet.setDate(oldTimeSet.getDate());
			newTimeSet.setStartTime(oldTimeSet.getStartTime() + Constant.TIME_SLOT_SHIFT);
			newTimeSet.setTimeOffset(oldTimeSet.getTimeOffset());
		} else {
		    // Shift to the next day, but not in the weekend 
			//String newDate = AddDateStr(oldTimeSet.getDate(), Constant.Date_SHIFT);
			String newDate = getNextValidDate(oldTimeSet.getDate());
			newTimeSet.setDate(newDate);
			newTimeSet.setStartTime(oldTimeSet.getStartTime());
			newTimeSet.setTimeOffset(oldTimeSet.getTimeOffset());
		}
		return newTimeSet;
	}
	
	
	
}
