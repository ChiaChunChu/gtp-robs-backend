package ie.cct.gtp.robs.config;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ie.cct.gtp.robs.constants.Rooms;
import ie.cct.gtp.robs.dao.RoomRepository;
import ie.cct.gtp.robs.entities.Room;

@Configuration
public class RoomConfig {

	@Bean
	CommandLineRunner runnerRoonconfig(RoomRepository roomRepository) {
		return args -> {
			ArrayList<Room> rooms = new ArrayList<>();
			
			for (Rooms r: Rooms.values()) {
				int num = r.getNumer();
				String roomType = r.toString();
				BigDecimal priceHour = r.getPricePerHour();
				BigDecimal priceDay = r.getPricePerDay();
				for (int i = 0; i < num; i++) {
					String roomName = r.toString()+String.valueOf(i+1);
					Room room = new Room(roomName, roomType, priceHour, priceDay);
					rooms.add(room);
				}
			}
			
			if (roomRepository.getTotleRoomNumber() == 0) {
			    roomRepository.saveAll(rooms);
			}
		};
	};
}
