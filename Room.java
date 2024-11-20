package HotelReservationSystem;

import java.util.Calendar;

public class Room {
    private int roomNumber;
    private boolean isAvailable;
    private String roomType;
    private double price;

    private String customerName;
    private String contactNumber;
    private int numberOfGuests;
    private Calendar checkInDate;
    private Calendar checkOutDate;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true; 
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public void book(String customerName, String contactNumber, int numberOfGuests, Calendar checkInDate, Calendar checkOutDate) {
        this.isAvailable = false; 
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void cancelBooking() {
        this.isAvailable = true; 
        this.customerName = null;
        this.contactNumber = null;
        this.numberOfGuests = 0;
        this.checkInDate = null;
        this.checkOutDate = null;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - $" + price + " per night" +
               (isAvailable ? " (Available)" : " (Booked by " + customerName + ")");
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public Calendar getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Calendar checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Calendar getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Calendar checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
