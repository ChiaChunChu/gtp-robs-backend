package ie.cct.gtp.robs.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ie.cct.gtp.robs.constants.UserRole;
import ie.cct.gtp.robs.dao.BookingRepository;
import ie.cct.gtp.robs.dao.BookingSlotRepository;
import ie.cct.gtp.robs.dao.UserRepository;
import ie.cct.gtp.robs.entities.User;

@Configuration
public class UserConfig {

    @Bean
	CommandLineRunner runnerUserConfig(
			UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			BookingRepository bookingRepository,
			BookingSlotRepository bookingSlotRepository) {
    	
    	String mollyEmail = "molly.chu@cct.com";
    	String eltonEmail = "elton.oliver@cct.com";
    	String password = "pass1234!";
    	
		return args -> {
			User molly = new User (
					"Molly",
					"Chu",
					mollyEmail,
					bCryptPasswordEncoder.encode(password),
					"0831234567",
					UserRole.ADMIN.toString()
					);
			
			User elton = new User (
					"Elton",
					"Oliver",
					eltonEmail,
					bCryptPasswordEncoder.encode(password),
					"0851234567",
					UserRole.ADMIN.toString()
					);
			
			if (!userRepository.findByEmail(mollyEmail).isPresent()) {
				userRepository.save(molly);
			}
			if (!userRepository.findByEmail(eltonEmail).isPresent()) {
				userRepository.save(elton);
			}
		
/**
 *  testing code below
 */
//			BookingSlot bookingSlot1 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 3, 1, 1);
//			BookingSlot bookingSlot2 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 4, 1, 2);
//			BookingSlot bookingSlot3 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 4, 2, 3);
//			BookingSlot bookingSlot4 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 5, 3, 4);
//			BookingSlot bookingSlot5 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 6, 1, 5);
//			BookingSlot bookingSlot6 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 4, 4, 6);
//			BookingSlot bookingSlot7 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 8, 1, 7);
//			BookingSlot bookingSlot8 = new BookingSlot(
//					LocalDate.of(2021, Month.AUGUST, 1), 7, 1, 8);
//			
//			Booking booking1 = new Booking(new BigDecimal(String.valueOf(100)), molly);
//			Booking booking2 = new Booking(new BigDecimal(String.valueOf(200)), molly);
//			Booking booking3 = new Booking(new BigDecimal(String.valueOf(300)), elton);
//			Booking booking4 = new Booking(new BigDecimal(String.valueOf(400)), elton);
//		
//			booking1.addBookingSlot(bookingSlot1);
//			booking1.addBookingSlot(bookingSlot2);
//			booking2.addBookingSlot(bookingSlot3);
//			booking2.addBookingSlot(bookingSlot4);
//			booking3.addBookingSlot(bookingSlot5);
//			booking4.addBookingSlot(bookingSlot6);
//			booking4.addBookingSlot(bookingSlot7);
//			booking4.addBookingSlot(bookingSlot8);
//			
//			bookingRepository.saveAll(List.of(booking1, booking2, booking3, booking4));
//			
//			//List<Booking> booking = bookingRepository.findBookingsByUserId(1L);
//			List<BookingSlot> bookingSlot = bookingSlotRepository.findBookingSlotByTimeCovered(LocalDate.of(2021, Month.AUGUST, 1), 5, 2);
//
//			System.out.println("get the slot");
//			for (BookingSlot b: bookingSlot) {
//				System.out.println(b.toString());
//			}
//			
//			List<BookingSlot> bookingSlotDate = bookingSlotRepository.findBookingSlotByDate(LocalDate.of(2021, Month.AUGUST, 1));
//			
//			System.out.println("get the slot by date");
//			for (BookingSlot b: bookingSlotDate) {
//				System.out.println(b.toString());
//			}
//			
//			bookingRepository.findById(1L).ifPresent(System.out::println);
//			System.out.println("--");
//			Booking bookingID1 = bookingRepository.findById(1L);
//			System.out.println("--");
//			bookingRepository.deleteById(2L);
//			System.out.println("--");
//			
//			bookingRepository.findById(2L).ifPresent(System.out::println);
//
//			
		};
	};
}
