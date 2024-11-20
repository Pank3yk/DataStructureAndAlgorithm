package HotelReservationSystem;

import java.sql.*;
import java.util.Calendar;

public class DBHHotelReservation {

    private static final String URL = "jdbc:mysql://localhost:3306/hrsdm";
    private static final String USER = "root";
    private static final String PASSWORD = "051220Anuar!"; 
    
    private Connection connection;

    public DBHHotelReservation() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String fullName, String email, String contactNumber, String validID, String password) {
        String query = "INSERT INTO hoteldatabase (fullName, email, contactNumber, validID, password) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, contactNumber);
            stmt.setString(4, validID);
            stmt.setString(5, password);
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValidUser(String email, String password) {
        String query = "SELECT * FROM hoteldatabase WHERE email = ? AND password = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password); 

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; 
    }


    public boolean saveBooking(int roomNumber, String customerName, String contactNumber, int numberOfGuests, java.util.Date checkInDate, java.util.Date checkOutDate) {
        String query = "INSERT INTO rooms (roomNumber, customerName, contactNumber, numberOfGuests, checkInDate, checkOutDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomNumber);
            stmt.setString(2, customerName);
            stmt.setString(3, contactNumber);
            stmt.setInt(4, numberOfGuests);
            stmt.setDate(5, new java.sql.Date(checkInDate.getTime()));
            stmt.setDate(6, new java.sql.Date(checkOutDate.getTime()));
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteBooking(int roomNumber, String customerName, String contactNumber, int numberOfGuests, java.util.Date checkInDate, java.util.Date checkOutDate) {
        String query = "DELETE FROM rooms WHERE roomNumber = ? AND customerName = ? AND contactNumber = ? AND numberOfGuests = ? AND checkInDate = ? AND checkOutDate = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomNumber);
            stmt.setString(2, customerName);
            stmt.setString(3, contactNumber);
            stmt.setInt(4, numberOfGuests);
            stmt.setDate(5, new java.sql.Date(checkInDate.getTime()));
            stmt.setDate(6, new java.sql.Date(checkOutDate.getTime()));
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public Connection getConnection() {
        return connection;
    }
}
