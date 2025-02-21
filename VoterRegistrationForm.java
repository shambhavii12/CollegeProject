package mycollegeproject.loginandsignup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class VoterRegistrationForm {
    private static final String FILE_NAME = "voters.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VoterRegistrationForm::createGUI);
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Voter Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(5, 2));
        frame.getContentPane().setBackground(Color.PINK);

        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameText = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageText = new JTextField();
        JLabel MobileLabel = new JLabel("Mobile Number:");
        JTextField MobileNoText = new JTextField();
        JButton submitButton = new JButton("Register");
        nameLabel.setForeground(Color.BLUE);
        ageLabel.setForeground(Color.BLUE);
        MobileLabel.setForeground(Color.BLUE);
        submitButton.setBackground(Color.YELLOW);
        submitButton.setForeground(Color.MAGENTA);

        frame.add(nameLabel);
        frame.add(nameText);
        frame.add(ageLabel);
        frame.add(ageText);
        frame.add(MobileLabel);
        frame.add(MobileNoText);
        frame.add(new JLabel());
        frame.add(submitButton);

        submitButton.addActionListener(e -> registerVoter(nameText, ageText, MobileNoText));
        frame.setVisible(true);
    }

    private static void registerVoter(JTextField nameText, JTextField ageText, JTextField MobileNoText) {
        String name = nameText.getText();
        String age = ageText.getText();
        String mobile = MobileNoText.getText();

        if (name.isEmpty() || age.isEmpty() || mobile.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }

        int age1;
        try {
            age1 = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age format");
            return;
        }

        if (age1 < 18) {
            JOptionPane.showMessageDialog(null, "Try again, Age is not eligible.");
            return;
        }

        // Database connection details
        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; // Adjust for your DB
        String user = "system"; // Change to your user name
        String password = "sham"; // Change to your password

        String query = "INSERT INTO VOTERS (full_name, age, mobile_number) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age1);
            pstmt.setString(3, mobile);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Voter registration successful!");

                // Clear form fields after successful registration
                SwingUtilities.invokeLater(() -> {
                    nameText.setText("");
                    ageText.setText("");
                    MobileNoText.setText("");
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
    }

}

