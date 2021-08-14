package ie.cct.gtp.robs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gtp.robs.entities.Booking;
import ie.cct.gtp.robs.exception.NoContentException;
import ie.cct.gtp.robs.exception.UnauthorizedException;
import ie.cct.gtp.robs.misc.BookingDetail;
import ie.cct.gtp.robs.misc.BookingRequest;
import ie.cct.gtp.robs.misc.BookingResponse;
import ie.cct.gtp.robs.misc.BookingSlotRequest;
import ie.cct.gtp.robs.misc.BookingSlotResponse;
import ie.cct.gtp.robs.security.AuthorizationConfirmer;
import ie.cct.gtp.robs.service.BookingService;
import ie.cct.gtp.robs.service.BookingSlotService;
import io.jsonwebtoken.JwtException;

@RestController
@RequestMapping("api/booking")
@CrossOrigin("*")
public class BookingController {

	
	private final AuthorizationConfirmer authConfirmer;
	private final BookingService bookingService;
	private final BookingSlotService bookingSlotService;
	
	@Autowired
	public BookingController(
			AuthorizationConfirmer authConfirmer,
			BookingService bookingService,
			BookingSlotService bookingSlotService) {
		this.authConfirmer = authConfirmer;
		this.bookingService = bookingService;
		this.bookingSlotService = bookingSlotService;
	}

	@GetMapping("/{userid}")
	public List<Booking> getUserBookings(
			@PathVariable("userid") Long id,
			@RequestHeader(name = "Authorization", required = true) String authorizationHeader
			) throws UnauthorizedException, JwtException {
		
		authConfirmer.isTokenValid(authorizationHeader);
		
		return bookingService.getUserBookings(id);
	}
	
	
	@PostMapping("/slotsrequest")
	public BookingSlotResponse checkDateAndSlotStatus(
			@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
			@RequestBody(required = true) BookingSlotRequest request) throws UnauthorizedException, JwtException {
		
		authConfirmer.isTokenValid(authorizationHeader);
		
		return bookingSlotService.checkDateAndSlotStatus(request);
		
	}
	
	@PostMapping("/{userid}/add")
	public BookingResponse addBooking(
			@PathVariable("userid") Long id,
			@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
			@RequestBody(required = true) List<BookingRequest> request) throws UnauthorizedException, JwtException {
	
		authConfirmer.isTokenValid(authorizationHeader);
		
		return bookingService.addBooking(id, request);
	}
	
	@GetMapping("/{bookingSerial}/invoice")
	public BookingDetail getBookingDetail(
			@PathVariable("bookingSerial") String bookingSerial,
			@RequestHeader(name = "Authorization", required = true) String authorizationHeader
			) throws UnauthorizedException, JwtException, NoContentException {
		
		authConfirmer.isTokenValid(authorizationHeader);
		
		return bookingService.getBookingDetail(bookingSerial);
	}
	
	
	
}
