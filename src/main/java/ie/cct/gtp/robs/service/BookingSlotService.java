package ie.cct.gtp.robs.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gtp.robs.dao.BookingSlotRepository;
import ie.cct.gtp.robs.entities.BookingSlot;
import ie.cct.gtp.robs.entities.Room;
import ie.cct.gtp.robs.misc.BookingSlotRequest;
import ie.cct.gtp.robs.misc.BookingSlotResponse;
import ie.cct.gtp.robs.misc.TimeSet;
import ie.cct.gtp.robs.utils.BookingSlotRequestConvert;
import ie.cct.gtp.robs.utils.CalcUtils;
import ie.cct.gtp.robs.utils.LocalDateUtils;

@Service
public class BookingSlotService {

	private final BookingSlotRepository bookingSlotRepository;
	private final RoomService roomService;
	
	@Autowired
	public BookingSlotService(
			BookingSlotRepository bookingSlotRepository,
			RoomService roomService) {
		this.bookingSlotRepository = bookingSlotRepository;
		this.roomService = roomService;
	}
	
	public List<BookingSlot> findBookingSlotByBookingId (Long id) {
		List<BookingSlot> slots = new ArrayList<>();
		bookingSlotRepository.findBookingSlotByBookingId(id).ifPresent(b->{
			b.forEach(s->{
				slots.add(s);	
			});
		});
		return slots;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public boolean isDateBooked(LocalDate date) {
		return bookingSlotRepository.findBookingSlotByDate(date).isPresent();
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public List<BookingSlot> findBookingSlotByDate(LocalDate date) {
		List<BookingSlot> bookingSlot = new ArrayList<>(); 
		bookingSlotRepository.findBookingSlotByDate(date).ifPresent(s ->{
			s.forEach(b->{
				bookingSlot.add(b);
			});
		});;
		return bookingSlot;
	}
	
	public List<BookingSlot> findBookingSlotByDateAndRoomType(LocalDate date, String roomType) {
		List<BookingSlot> bookingSlot = findBookingSlotByDate(date);
		List<BookingSlot> bookingSlotRoom = new ArrayList<>();
		for (BookingSlot s: bookingSlot) {
			if (s.getRoomName().equals(roomType)) {
				bookingSlotRoom.add(s);
			}
		}
		return bookingSlotRoom;
	}
	
	/**
	 * 
	 * @param date
	 * @param startTime
	 * @param timeOffset
	 * @return
	 */
	public List<BookingSlot> findBookingSlotByTimeCovered(LocalDate date, Integer startTime, Integer timeOffset) {
		List<BookingSlot> bookingSlot = new ArrayList<>(); 
		bookingSlotRepository.findBookingSlotByTimeCovered(date, startTime, timeOffset).ifPresent(s ->{
			s.forEach(b->{
				bookingSlot.add(b);
			});
		});;
		return bookingSlot;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public boolean isSlotOccupied(BookingSlotRequest request) {
		
		// convert the date format
		LocalDate date = LocalDateUtils.StrToLocalDate(request.getDate());
		if (!isDateBooked(date)) {
			return false;
		}
		System.out.println("--molly --isSlotOccupied");
		List<BookingSlot> bookingSlot = findBookingSlotByTimeCovered(
				date, request.getStartTime(), request.getTimeOffset());
		if (bookingSlot.size() == 0) {
			return false;
		}
		System.out.println("--molly --return true");
		return true;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public List<BookingSlot> getSlotCovered(BookingSlotRequest request) {
		// convert the date format
		LocalDate date = LocalDateUtils.StrToLocalDate(request.getDate());
		List<BookingSlot> bookingSlot = findBookingSlotByTimeCovered(
				date, request.getStartTime(), request.getTimeOffset());
		return bookingSlot;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean isSlotAvailable(BookingSlotRequest request) {
		if (!isSlotOccupied(request)) {
			return true;
		}
		/**
		 *  slot is covered by booking(s)...
		 *  check if the room is available
		 */
		List<BookingSlot> bookingSlot = getSlotCovered(request);
		if (roomService.hasRoomAvailable(bookingSlot, request.getRoomType())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public BookingSlotRequest getNextAvailableSlot(BookingSlotRequest request) {
		
		BookingSlotRequest nextReq = new BookingSlotRequest(
				request.getDate(),
				request.getStartTime(),
				request.getTimeOffset(),
				request.getRoomType());

		TimeSet oldTimeSet = new TimeSet(request.getDate(), request.getStartTime(), request.getTimeOffset());
		
		TimeSet newTimeSet;
		do {
			newTimeSet = LocalDateUtils.getNextValidTimeSet(oldTimeSet);
			
			nextReq.setDate(newTimeSet.getDate());
			nextReq.setStartTime(newTimeSet.getStartTime());
			nextReq.setTimeOffset(newTimeSet.getTimeOffset());
			oldTimeSet = newTimeSet;
			
		} while (!isSlotAvailable(nextReq));

		return nextReq;
	}

	public boolean isDateValid(BookingSlotRequest request) {
		LocalDate localDate = LocalDateUtils.StrToLocalDate(request.getDate());
		return !(LocalDateUtils.isWeekend(localDate) || LocalDateUtils.isHoliday(localDate));
	}
		
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public BookingSlotResponse checkDateAndSlotStatus(BookingSlotRequest request) {
		
		BookingSlotRequest validReq;
		boolean isOkToBook;
		
		if (isDateValid(request)) { // the date is not weekend or holiday
			if (isSlotAvailable(request)) { // slot is available
				isOkToBook = true;
				validReq = request;
			} else { // slot is occupied
				isOkToBook = false;
				validReq = getNextAvailableSlot(request); // get the suggestion day
			}
		} else { // the date is weekend or holiday
			isOkToBook = false;
			validReq = getNextAvailableSlot(request); // get the suggestion day
		}
		
		// assign room, (room name)
		List<BookingSlot> bookingSlots = getSlotCovered(validReq);
		String roomAssign = roomService.assignRoom(bookingSlots, validReq);
		
		// convert BookingSlotRequest to BookingSlot
		BookingSlot convertedSlot = BookingSlotRequestConvert.RequestToBookingSlot(validReq, roomAssign);

		// calculate the money
		Room room = roomService.getRoomByRoomName(convertedSlot.getRoomName());
		BigDecimal amount = CalcUtils.slotAmountCalc(convertedSlot, room);
		
		BookingSlotResponse response = new BookingSlotResponse(isOkToBook, convertedSlot, amount);		
		
		return response;
	}
	
}
