package HotelReservationSystem;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;


    public static boolean isLoggedIn = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 615, 405);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(34, 38, 41));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

      
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(263, 160, 135, 38);
        contentPane.add(lblNewLabel);

      
        JLabel lblNewLabel_1 = new JLabel("Email");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(112, 217, 58, 15);
        contentPane.add(lblNewLabel_1);

   
        textField = new JTextField();
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(42, 47, 51));
        textField.setBounds(202, 208, 203, 38);
        contentPane.add(textField);
        textField.setColumns(10);

   
        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(112, 265, 80, 15);
        contentPane.add(lblNewLabel_1_1);

      
        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setBackground(new Color(42, 47, 51));
        passwordField.setBounds(202, 256, 203, 38);
        contentPane.add(passwordField);

   
        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = new String(passwordField.getPassword());
                HeroPage hero = new HeroPage();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Email and Password!");
                } else {
                    DBHHotelReservation dbTest = new DBHHotelReservation();
                    if (dbTest.isValidUser(email, password)) {
                     
                        isLoggedIn = true;
                        JOptionPane.showMessageDialog(null, "Login successful! Returning to the Main Page");
                        hero.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or password.");
                    }
                }
            }
        });
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(34, 38, 41));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(263, 304, 85, 21);
        contentPane.add(btnNewButton);

    
        JLabel lblNewLabel_2 = new JLabel("New label");
        ImageIcon originalIcon = new ImageIcon(Login.class.getResource("/HotelReservationSystem/Login.png"));
        Image resizedImage = originalIcon.getImage().getScaledInstance(328, 203, Image.SCALE_SMOOTH);
        lblNewLabel_2.setIcon(new ImageIcon(resizedImage));
        lblNewLabel_2.setBounds(140, 0, 271, 150);
        contentPane.add(lblNewLabel_2);


        JButton btnNoAccountYet = new JButton("No account yet? Register");
        btnNoAccountYet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrationGUI reg = new RegistrationGUI();
                reg.setVisible(true);
                dispose(); 
            }
        });
        btnNoAccountYet.setForeground(Color.WHITE);
        btnNoAccountYet.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNoAccountYet.setBackground(new Color(34, 38, 41));
        btnNoAccountYet.setBounds(207, 335, 198, 21);
        contentPane.add(btnNoAccountYet);
    }
}
