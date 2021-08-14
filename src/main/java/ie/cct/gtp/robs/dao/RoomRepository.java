package ie.cct.gtp.robs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ie.cct.gtp.robs.entities.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("SELECT r.id FROM Room r WHERE r.roomName = ?1")
	Integer getRoomIdByRoomName(String roomName);

	@Query("SELECT r FROM Room r WHERE r.id = ?1")
	Room getRoomById(Integer id);
	
	@Query("SELECT r FROM Room r WHERE r.roomType = ?1")
	Optional<List<Room>> findRoomsByRoomType(String roomType);
	
	@Query("SELECT r FROM Room r WHERE r.roomName = ?1")
	Optional<Room> findRoomByRoomName(String roomName);
	
	@Query("SELECT COUNT(r.id) FROM Room r WHERE r.roomType = ?1")
	Integer getNumberByRoomType(String roomType);
	
	@Query("SELECT COUNT(r.id) FROM Room r")
	Integer getTotleRoomNumber();
	
	@Query("SELECT DISTINCT r.roomType FROM Room r")
	Optional<List<String>> getRoomTypes();
	
	@Query("SELECT r.roomType FROM Room r WHERE r.roomName = ?1")
	String getRoomTypeByRoomName(String roomName);
	
}
