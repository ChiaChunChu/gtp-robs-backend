package ie.cct.gtp.robs.misc;

import java.util.HashMap;
import java.util.Map;

public class RoomStatus {

	// Map <room name, room status>
	private Map<String, Boolean> rooms;
	
	public RoomStatus(String roomType, Integer num) {
		super();
		this.rooms = initRooms(roomType, num);	
	}

	public RoomStatus() {
		super();
	}
	
	private Map<String, Boolean> initRooms(String roomType, Integer num) {

		rooms = new HashMap<String, Boolean>(); 
		
		for (int i=0; i<num; i++) {
			String roomName = roomType+String.valueOf(i+1);
			rooms.put(roomName, false);
		}
		return this.rooms;
	}
	
	public Map<String, Boolean> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Boolean> rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public String toString() {
		return "RoomStatus [rooms=" + rooms + "]";
	}
}
