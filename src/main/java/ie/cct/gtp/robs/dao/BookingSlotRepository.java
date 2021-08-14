package ie.cct.gtp.robs.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ie.cct.gtp.robs.entities.BookingSlot;

@Repository
public interface BookingSlotRepository extends JpaRepository<BookingSlot, Long> {

	@Query("SELECT b FROM BookingSlot b WHERE b.booking.id = ?1")
	Optional<List<BookingSlot>> findBookingSlotByBookingId(Long id); 
	
	@Query("SELECT b FROM BookingSlot b WHERE b.date = ?1")
	Optional<List<BookingSlot>> findBookingSlotByDate(LocalDate date);
	
	@Query("SELECT b FROM BookingSlot b "
			+ "WHERE (b.startTime = ?2 "
			+ "or ((b.startTime + b.timeOffset) > ?2 and (b.startTime + b.timeOffset) < (?2+?3)) "
			+ "or (b.startTime > ?2 and b.startTime < (?2+?3)) "
			+ "or (b.startTime < ?2 and (b.startTime + b.timeOffset) > (?2+?3))) "
			+ "and b.date = ?1")
	Optional<List<BookingSlot>> findBookingSlotByTimeCovered(LocalDate date, Integer startTime, Integer timeOffset);
	
	@Query("SELECT b FROM BookingSlot b WHERE b.roomName = ?1")
	Optional<List<BookingSlot>> findBookingSlotByRoomName(String roomName);
	
	
}
