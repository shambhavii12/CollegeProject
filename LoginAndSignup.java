package mycollegeproject.loginandsignup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginAndSignup {
    private static final String FILE_NAME = "users.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginAndSignup().createGUI());
    }

    private void createGUI() {
        JFrame frame = new JFrame("Login & Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(3, 1));
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        
        
        JLabel userLabel = new JLabel("Phone Number:");
        JTextField userText = new JTextField(20);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(20);
        JButton loginButton = new JButton("Log In");
        JButton signupButton = new JButton("Sign Up");
        userLabel.setForeground(Color.BLUE);
        passLabel.setForeground(Color.BLUE);
        loginButton.setBackground(Color.GREEN);
        signupButton.setForeground(Color.BLACK);
        
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(loginButton);
        panel.add(signupButton);
        
        frame.add(panel);
        
        loginButton.addActionListener(e -> logIn(userText.getText(), new String(passText.getPassword())));
        signupButton.addActionListener(e -> signUp(userText.getText(), new String(passText.getPassword())));
        
        frame.setVisible(true);
    }

    private void signUp(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter username and password");
            return;
        }
        
        if (isUserExists(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists");
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Signup successful!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user");
        }
    }

    private void logIn(String username, String password) {
        if (validateUser(username, password)) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            VoterRegistrationForm.main(new String[]{}); // Open the voter registration form
            
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
        }
    }
   


    private boolean isUserExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data");
        }
        return false;
    }

    private boolean validateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data");
        }
        return false;
    }
}
