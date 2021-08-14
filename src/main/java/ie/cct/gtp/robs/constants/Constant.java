package ie.cct.gtp.robs.constants;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Constant {

	/**
	 * The number of compartment rooms, large meeting rooms,
	 * medium size meeting rooms and small meeting rooms.
	 */
	public static final Integer COMPARTMENT_NUM = 48;
	public static final Integer L_MEETING_NUM = 4;
	public static final Integer M_MEETING_NUM = 6;
	public static final Integer S_MEETING_NUM = 8;
	
	/**
	 * The price (per hour/day) for each room
	 */
	public static final Integer DAY_TIME_OFFSET = 12;
	public static final BigDecimal COMPARTMENT_PRICE_HOUR = new BigDecimal(String.valueOf(2));
	public static final BigDecimal COMPARTMENT_PRICE_DAY = new BigDecimal(String.valueOf(20));
	public static final BigDecimal L_MEETING_PRICE_HOUR = new BigDecimal(String.valueOf(32));
	public static final BigDecimal L_MEETING_PRICE_DAY = new BigDecimal(String.valueOf(320));
	public static final BigDecimal M_MEETING_PRICE_HOUR = new BigDecimal(String.valueOf(20));
	public static final BigDecimal M_MEETING_PRICE_DAY = new BigDecimal(String.valueOf(200));
	public static final BigDecimal S_MEETING_PRICE_HOUR = new BigDecimal(String.valueOf(12));
	public static final BigDecimal S_MEETING_PRICE_DAY = new BigDecimal(String.valueOf(120));
	
	/**
	 * The opening date and time
	 */
	public static final Integer OPEN_TIME = 7;
	public static final Integer CLOSE_TIME = 19;
	public static final Integer TIME_SLOT_SHIFT = 1;
	public static final Integer Date_SHIFT = 1;
	public static final DayOfWeek[] WEEKEND = {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
	public static final LocalDate[] HOLIDAY = {
			LocalDate.of(2021, Month.AUGUST, 2),
			LocalDate.of(2021, Month.OCTOBER, 25),
			LocalDate.of(2021, Month.DECEMBER, 24),
			LocalDate.of(2021, Month.DECEMBER, 25)
	};
}
