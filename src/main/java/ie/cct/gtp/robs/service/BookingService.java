package ie.cct.gtp.robs.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gtp.robs.dao.BookingRepository;
import ie.cct.gtp.robs.dao.BookingSlotRepository;
import ie.cct.gtp.robs.entities.Booking;
import ie.cct.gtp.robs.entities.BookingSlot;
import ie.cct.gtp.robs.entities.Room;
import ie.cct.gtp.robs.entities.User;
import ie.cct.gtp.robs.exception.NoContentException;
import ie.cct.gtp.robs.misc.BookingDetail;
import ie.cct.gtp.robs.misc.BookingRequest;
import ie.cct.gtp.robs.misc.BookingResponse;
import ie.cct.gtp.robs.misc.BookingSlotDetail;
import ie.cct.gtp.robs.utils.CalcUtils;
import ie.cct.gtp.robs.utils.LocalDateUtils;

@Service
public class BookingService {

	private final UserService userService;
	private final BookingSlotRepository bookingSlotRepository;
	private final BookingRepository bookingRepository;
	private final BookingSlotService bookingSlotService;
	private final RoomService roomService;
	
	@Autowired
	public BookingService(
			UserService userService,
			BookingSlotRepository bookingSlotRepository,
			BookingRepository bookingRepository,
			BookingSlotService bookingSlotService,
			RoomService roomService) {
		this.userService = userService;
		this.bookingSlotRepository = bookingSlotRepository;
		this.bookingRepository = bookingRepository;
		this.bookingSlotService = bookingSlotService;
		this.roomService = roomService;
	}
	
	
	public void insertBookingSlot(BookingSlot bookingSlot) {
		bookingSlotRepository.save(bookingSlot);
	}
	
	public Booking insertBooking(Booking booking) {
		return bookingRepository.saveAndFlush(booking);
	}
	
	public Booking findBookingByBookingSerial(String bookingSerial) {
		Booking booking = new Booking();
		bookingRepository.findBookingByBookingSerial(bookingSerial).ifPresent(s->{
			booking.setId(s.getId());
			booking.setBookingSerial(s.getBookingSerial());
			booking.setAmount(s.getAmount());
			booking.setUser(s.getUser());	
		});
		return booking;
	}
	
	public Integer getBookingNumbers() {
		return bookingRepository.getBookingNumbers();
	}

	
	public List<Booking> getUserBookings(Long id) {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findBookingsByUserId(id).ifPresent(b->{
			b.forEach(s->{
				bookings.add(s);
			});
		});
		return bookings;
	}
	
	
	public BookingResponse addBooking(
			Long userId,
			List<BookingRequest> request) {
				
		// convert the request and calculate the total money
		List<BookingSlot> slots = new ArrayList<>();
		BigDecimal amount = new BigDecimal(0);
		for (BookingRequest r: request) {
			LocalDate date = LocalDateUtils.StrToLocalDate(r.getDate());
			slots.add(new BookingSlot(date, r.getStartTime(), r.getTimeOffset(), r.getRoomName()));
			
			Room room = roomService.getRoomByRoomName(r.getRoomName());
			amount = amount.add(CalcUtils.amountCalc(r, room));
		}
				
		// get the user entity
		User user = userService.getUserById(userId);
		
		// get the booking serial 
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		String serialNum = String.format("%05d", getBookingNumbers()+1);
		String bookingSerial = today.format(formatter)+serialNum;
	  	
		System.out.println("--bookingSerial");
		System.out.println(bookingSerial);
		
		Booking booking = new Booking (bookingSerial, amount, user);
//		Booking booking = new Booking (amount, user);
		
		for (BookingSlot s: slots) {
			booking.addBookingSlot(s);
		}
		
		Booking bookingSaved = insertBooking(booking);
		
		return new BookingResponse(bookingSaved.getId(), bookingSaved.getBookingSerial(), bookingSaved.getAmount());
		
	}
	
	public BookingDetail getBookingDetail(String bookingSerial) throws NoContentException{
		
		Booking booking = findBookingByBookingSerial(bookingSerial);
		if (booking == null) {
			throw new NoContentException (
					"Not found booking information for booking serial number: "+bookingSerial);
		}
		List<BookingSlot> bookingSlots = bookingSlotService.findBookingSlotByBookingId(booking.getId());
		if (bookingSlots.size() == 0) {
			throw new NoContentException (
					"Not found booking slots for booking serial number: "+bookingSerial);
		}
		
		List<BookingSlotDetail> slotsDetail = new ArrayList<>();
		for (BookingSlot s: bookingSlots) {
			BookingSlotDetail slotDetail = new BookingSlotDetail (
					s.getDate(),
					s.getStartTime(),
					s.getTimeOffset(),
					s.getRoomName(),
					roomService.getRoomByRoomName(s.getRoomName()));
			
			slotsDetail.add(slotDetail);
		}
		
		BookingDetail bookingDetail = new BookingDetail(
				booking.getUser().getFirstname(),
				booking.getUser().getLastname(),
				booking.getUser().getEmail(),
				slotsDetail,
				booking.getAmount()
				);
		
		return bookingDetail;
	}
	
	
}
