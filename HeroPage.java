package HotelReservationSystem;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeroPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HeroPage frame = new HeroPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressWarnings("removal")
    public HeroPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1218, 812);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        ImageIcon originalIcon = new ImageIcon(HeroPage.class.getResource("/HotelReservationSystem/pexels-photo-4223678new.png"));
        contentPane.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1210, 768);
        contentPane.add(layeredPane);

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(0, 0, 1272, 786);
        layeredPane.add(lblNewLabel, new Integer(0));
        lblNewLabel.setIcon(new ImageIcon(HeroPage.class.getResource("/HotelReservationSystem/pexels-photo-4223678new.png")));

       
        JButton loginLogout = new JButton(Login.isLoggedIn ? "Logout" : "Login");
        loginLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Login.isLoggedIn) {
                
                    int response = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to log out?",
                            "Confirm", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        Login.isLoggedIn = false; 
                        JOptionPane.showMessageDialog(null, "You have successfully logged out.");
                        loginLogout.setText("Login"); 
                    }
                } else {
                   
                    int response = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to proceed to login?",
                            "Confirm", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        Login login = new Login(); 
                        login.setVisible(true);
                        dispose(); 
                    }
                }
            }
        });
        loginLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
        loginLogout.setForeground(new Color(255, 255, 255));
        loginLogout.setBounds(678, 527, 188, 72);
        loginLogout.setBackground(new Color(0, 0, 0, 0));
        loginLogout.setOpaque(false);
        loginLogout.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        layeredPane.add(loginLogout, new Integer(2));

      
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to proceed?",
                        "Confirm", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    RegistrationGUI reg = new RegistrationGUI();
                    reg.setVisible(true);
                    dispose(); 
                }
            }
        });
        btnRegister.setForeground(new Color(255, 255, 255));
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnRegister.setBounds(941, 527, 188, 72);
        btnRegister.setBackground(new Color(0, 0, 0, 0));
        btnRegister.setOpaque(false);
        btnRegister.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        layeredPane.add(btnRegister, new Integer(3));

     
        JButton btnRoomManagement = new JButton("Room Management");
        btnRoomManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Login.isLoggedIn) {
                    RoomManagementGUI rm = new RoomManagementGUI(); 
                    rm.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Log in to proceed!", "Login Required", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnRoomManagement.setForeground(new Color(255, 255, 255));
        btnRoomManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnRoomManagement.setBounds(96, 527, 188, 72);
        btnRoomManagement.setBackground(new Color(0, 0, 0, 0));
        btnRoomManagement.setOpaque(false);
        btnRoomManagement.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        layeredPane.add(btnRoomManagement, new Integer(4));

       
        JButton btnCheckBooking = new JButton("Booking Details");
        btnCheckBooking.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnCheckBooking.setForeground(new Color(255, 255, 255));
        btnCheckBooking.setBackground(new Color(240, 240, 240));
        btnCheckBooking.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCheckBooking.setBounds(394, 527, 188, 72);
        btnCheckBooking.setBackground(new Color(0, 0, 0, 0));
        btnCheckBooking.setOpaque(false);
        btnCheckBooking.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        layeredPane.add(btnCheckBooking, new Integer(5));

      
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(HeroPage.class.getResource("/HotelReservationSystem/Gold and Black Hotel Resort Creative Logo.png")));
        lblLogo.setBounds(379, 27, 676, 429);
        layeredPane.add(lblLogo, new Integer(6));

       
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Image resizedImage = originalIcon.getImage().getScaledInstance(layeredPane.getWidth(), layeredPane.getHeight(), Image.SCALE_SMOOTH);
                lblNewLabel.setIcon(new ImageIcon(resizedImage));
            }
        });
    }
}
