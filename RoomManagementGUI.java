package HotelReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private RoomManager roomManager;
    private DefaultListModel<String> listModel;
    private JList<String> roomList;

    public RoomManagementGUI() {
        roomManager = new RoomManager();
        setTitle("Room Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        listModel = new DefaultListModel<>();
        updateRoomList();

        roomList = new JList<>(listModel);
        roomList.setBackground(new Color(34, 38, 41));
        roomList.setForeground(Color.WHITE);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(roomList);

        JButton bookButton = new JButton("Book Room");
        JButton cancelButton = new JButton("Cancel Booking");
        configureButton(bookButton);
        configureButton(cancelButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = roomList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Room selectedRoom = roomManager.getAllRooms().get(selectedIndex);
                    if (selectedRoom.isAvailable()) {
                        BookingGUI bookingGUI = new BookingGUI(roomManager, selectedRoom, () -> updateRoomList());
                        bookingGUI.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(RoomManagementGUI.this, "Room is already booked.");
                    }
                } else {
                    JOptionPane.showMessageDialog(RoomManagementGUI.this, "Please select a room to book.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = roomList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Room selectedRoom = roomManager.getAllRooms().get(selectedIndex);
                    if (!selectedRoom.isAvailable()) {
                        roomManager.cancelBooking(selectedRoom.getRoomNumber());
                        JOptionPane.showMessageDialog(RoomManagementGUI.this, "Booking for Room " + selectedRoom.getRoomNumber() + " has been canceled.");
                        updateRoomList();
                    } else {
                        JOptionPane.showMessageDialog(RoomManagementGUI.this, "Room is already available.");
                    }
                } else {
                    JOptionPane.showMessageDialog(RoomManagementGUI.this, "Please select a room to cancel.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void configureButton(JButton button) {
        button.setBackground(new Color(34, 38, 41));
        button.setForeground(Color.WHITE);
    }

    private void updateRoomList() {
        listModel.clear();
        for (Room room : roomManager.getAllRooms()) {
            listModel.addElement(room.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RoomManagementGUI gui = new RoomManagementGUI();
            gui.setVisible(true);
        });
    }
}
