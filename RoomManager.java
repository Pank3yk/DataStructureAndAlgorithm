package HotelReservationSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RoomManager {
    private List<Room> rooms;

    public RoomManager() {
        rooms = new ArrayList<>();
        rooms.add(new Room(101, "Standard Room", 150.0));
        rooms.add(new Room(102, "Deluxe Room", 250.0));
        rooms.add(new Room(103, "Executive Suite", 300.0));
        rooms.add(new Room(104, "Family Suite", 280.0));
        rooms.add(new Room(105, "King Room", 220.0));
        rooms.add(new Room(106, "Queen Room", 180.0));
        rooms.add(new Room(107, "Penthouse Suite", 400.0));
        rooms.add(new Room(108, "Junior Suite", 260.0));
        rooms.add(new Room(109, "Ocean View Room", 320.0));
        rooms.add(new Room(110, "Honeymoon Suite", 350.0));
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public boolean bookRoom(Room room, String customerName, String contactNumber, int numberOfGuests, Calendar checkInDate, Calendar checkOutDate) {
        if (room.isAvailable()) {
            room.book(customerName, contactNumber, numberOfGuests, checkInDate, checkOutDate);
            return true;
        }
        return false;
    }

    public void cancelBooking(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                String customerName = room.getCustomerName(); 
                String contactNumber = room.getContactNumber(); 
                int numberOfGuests = room.getNumberOfGuests(); 
                Calendar checkInDate = room.getCheckInDate(); 
                Calendar checkOutDate = room.getCheckOutDate(); 

                room.cancelBooking();
                
                // Call to delete booking from the database
                DBHHotelReservation dbHandler = new DBHHotelReservation();
                dbHandler.deleteBooking(roomNumber, customerName, contactNumber, numberOfGuests, checkInDate.getTime(), checkOutDate.getTime());
                break;
            }
        }
    }
}
