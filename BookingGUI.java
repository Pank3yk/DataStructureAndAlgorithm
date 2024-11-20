package HotelReservationSystem;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class BookingGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldName;
    private JTextField textFieldContact;
    private JLabel lblContactNumber;
    private JSpinner checkInSpinner;
    private JSpinner checkOutSpinner;
    private JSpinner guestSpinner; 
    private JLabel lblNumberOfGuest;
    private JLabel dialogueLabel;
    private RoomManager roomManager;
    private Room selectedRoom;
    private Runnable onBookingSuccess; 
    private JLabel lblNewLabel;
	private DBHHotelReservation dbHandler;

    /**
     * Launch the application for testing.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RoomManager roomManager = new RoomManager(); 
                Room testRoom = roomManager.getAllRooms().get(0); 
                BookingGUI frame = new BookingGUI(roomManager, testRoom, () -> {
                   
                    System.out.println("Room booked successfully.");
                });
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public BookingGUI(RoomManager roomManager, Room selectedRoom, Runnable onBookingSuccess) {
        this.roomManager = roomManager;
        this.selectedRoom = selectedRoom;
        this.onBookingSuccess = onBookingSuccess;
        this.dbHandler = new DBHHotelReservation();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 619, 435);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(34, 38, 41));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        

        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCustomerName.setForeground(new Color(255, 255, 255));
        lblCustomerName.setBounds(10, 117, 120, 13);
        contentPane.add(lblCustomerName);
        
        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldName.setForeground(new Color(255, 255, 255));
        textFieldName.setBackground(new Color(34, 38, 41)); 
        textFieldName.setBounds(144, 103, 296, 41);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);
        
        textFieldContact = new JTextField();
        textFieldContact.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldContact.setForeground(Color.WHITE); 
        textFieldContact.setBackground(new Color(34, 38, 41)); 
        textFieldContact.setBounds(144, 154, 296, 41);
        contentPane.add(textFieldContact);
        
        lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setForeground(Color.WHITE);
        lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblContactNumber.setBounds(10, 170, 120, 13);
        contentPane.add(lblContactNumber);
        

        JLabel lblCheckInDate = new JLabel("Check-in Date");
        lblCheckInDate.setForeground(Color.WHITE);
        lblCheckInDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCheckInDate.setBounds(10, 217, 120, 13);
        contentPane.add(lblCheckInDate);
        
        checkInSpinner = new JSpinner(new SpinnerDateModel());
        checkInSpinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
        checkInSpinner.setEditor(new JSpinner.DateEditor(checkInSpinner, "yyyy-MM-dd"));
        checkInSpinner.setBounds(144, 205, 296, 41);
        checkInSpinner.setBackground(new Color(34, 38, 41)); 
        JComponent editor = checkInSpinner.getEditor();
        if (editor instanceof JSpinner.DateEditor) {
            ((JSpinner.DateEditor) editor).getTextField().setForeground(Color.WHITE); 
            ((JSpinner.DateEditor) editor).getTextField().setBackground(new Color(34, 38, 41)); 
        }
        contentPane.add(checkInSpinner);
        
      
        JLabel lblCheckOutDate = new JLabel("Check-out Date");
        lblCheckOutDate.setForeground(Color.WHITE);
        lblCheckOutDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCheckOutDate.setBounds(10, 267, 120, 13);
        contentPane.add(lblCheckOutDate);
        
        checkOutSpinner = new JSpinner(new SpinnerDateModel());
        checkOutSpinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
        checkOutSpinner.setEditor(new JSpinner.DateEditor(checkOutSpinner, "yyyy-MM-dd"));
        checkOutSpinner.setBounds(144, 255, 296, 41);
        checkOutSpinner.setBackground(new Color(34, 38, 41));
        JComponent editor2 = checkOutSpinner.getEditor();
        if (editor2 instanceof JSpinner.DateEditor) {
            ((JSpinner.DateEditor) editor2).getTextField().setForeground(Color.WHITE); 
            ((JSpinner.DateEditor) editor2).getTextField().setBackground(new Color(34, 38, 41));
        }
        contentPane.add(checkOutSpinner);
        

        Calendar calendar = Calendar.getInstance();
        checkInSpinner.setValue(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1); 
        checkOutSpinner.setValue(calendar.getTime());
        

        guestSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1)); 
        guestSpinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
        guestSpinner.setBackground(new Color(34, 38, 41)); 
        guestSpinner.setBounds(144, 306, 46, 41);
        contentPane.add(guestSpinner);
        
        lblNumberOfGuest = new JLabel("Guests");
        lblNumberOfGuest.setForeground(Color.WHITE);
        lblNumberOfGuest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNumberOfGuest.setBounds(10, 320, 120, 13);
        contentPane.add(lblNumberOfGuest);

        dialogueLabel = new JLabel("Maximum guests reached!");
        dialogueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        dialogueLabel.setForeground(new Color(255, 2, 9));
        dialogueLabel.setBounds(200, 320, 190, 13);
        dialogueLabel.setVisible(false);
        contentPane.add(dialogueLabel);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setForeground(new Color(255, 255, 255));
        btnSubmit.setBackground(new Color(34, 38, 41));
        btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSubmit.setBounds(475, 356, 103, 32);
        contentPane.add(btnSubmit);
        
        lblNewLabel = new JLabel("ENTER BOOKING DETAILS");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setBounds(128, 23, 369, 49);
        contentPane.add(lblNewLabel);
        

        guestSpinner.addChangeListener(e -> {
            int currentValue = (Integer) guestSpinner.getValue();
            dialogueLabel.setVisible(currentValue >= 4);
        });

        btnSubmit.addActionListener(e -> handleBooking());
    }

    private void handleBooking() {
        String name = textFieldName.getText().trim();
        String contact = textFieldContact.getText().trim();
        int guests = (Integer) guestSpinner.getValue();
        Calendar checkInDate = Calendar.getInstance();
        Calendar checkOutDate = Calendar.getInstance();
        checkInDate.setTime((java.util.Date) checkInSpinner.getValue());
        checkOutDate.setTime((java.util.Date) checkOutSpinner.getValue());

        if (name.isEmpty() || contact.isEmpty() || guests <= 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (checkInDate.after(checkOutDate)) {
            JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }



        if (roomManager.bookRoom(selectedRoom, name, contact, guests, checkInDate, checkOutDate)) {

            boolean saved = dbHandler.saveBooking(
                selectedRoom.getRoomNumber(),
                name,
                contact,
                guests,
                checkInDate.getTime(),
                checkOutDate.getTime()
            );

            if (saved) {
                onBookingSuccess.run(); 
                JOptionPane.showMessageDialog(this, "Booking Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save booking in database.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Booking failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}