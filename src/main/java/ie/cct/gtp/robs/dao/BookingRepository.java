package ie.cct.gtp.robs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ie.cct.gtp.robs.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	
	@Query("SELECT b FROM Booking b WHERE b.user.id = ?1")
	Optional<List<Booking>> findBookingsByUserId(Long id);
	
	@Query("SELECT b FROM Booking b WHERE b.bookingSerial = ?1")
	Optional<Booking> findBookingByBookingSerial(String bookingSerial);
	
	@Query("SELECT COUNT(b.id) FROM Booking b")
	Integer getBookingNumbers();
}
