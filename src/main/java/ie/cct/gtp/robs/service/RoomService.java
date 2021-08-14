package ie.cct.gtp.robs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gtp.robs.dao.RoomRepository;
import ie.cct.gtp.robs.entities.BookingSlot;
import ie.cct.gtp.robs.entities.Room;
import ie.cct.gtp.robs.misc.BookingSlotRequest;
import ie.cct.gtp.robs.misc.RoomStatus;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	
	@Autowired
	public RoomService(
			RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	public Integer getNumberByRoomType(String roomType) {
		return roomRepository.getNumberByRoomType(roomType);
	}
	
	public Room getRoomByRoomName(String roomName) {
		Room room = roomRepository.findRoomByRoomName(roomName).get();
		return room;
	}
	
	public RoomStatus getRoomStatus(List<BookingSlot> bookingSlots, String roomType) {

		// Create Room Status Map
		int roomNum = getNumberByRoomType(roomType);
		RoomStatus roomSts = new RoomStatus(roomType, roomNum);
		
		// update the status of room
		for (BookingSlot s: bookingSlots) {
			if (roomSts.getRooms().containsKey(s.getRoomName())) {
				roomSts.getRooms().put(s.getRoomName(), true);
			}
		}
		
		return roomSts;
	}
	
	
	public boolean hasRoomAvailable(List<BookingSlot> bookingSlots, String roomType) {
		
		for (BookingSlot s: bookingSlots) {
			System.out.println(s.toString());
		}
		
		RoomStatus roomSts = getRoomStatus(bookingSlots, roomType);	
		boolean hasAvailableRoom = roomSts.getRooms().containsValue(false);
		
		return hasAvailableRoom;
	}
	
	public String assignRoom(List<BookingSlot> bookingSlots, BookingSlotRequest request) {

		RoomStatus roomSts = getRoomStatus(bookingSlots, request.getRoomType());
		String roomAssign = "";

		for (Map.Entry<String, Boolean> entry: roomSts.getRooms().entrySet()) {
			if (!entry.getValue()) {
				roomAssign = entry.getKey();
				break;
			}			
		}
		
		return roomAssign;
	}
	
	
	
	
}
