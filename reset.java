package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.*;

public  class reset {
	//public reset() {
	
	public static void main(String[] args) {
		JFrame view = new JFrame("Reset Your Password");
		view.setBounds(10, 10, 450, 350);
		view.getContentPane().setBackground(Color.pink);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLayout(null);
		view.setResizable(false);
		view.setVisible(true);
		
		JLabel NewPassword = new JLabel("Enter your New Password:");
		NewPassword.setBounds(20, 50, 150, 50);
		view.add(NewPassword);
		
		JLabel ConfirmPassword = new JLabel("Confirm Your New Password:");
		ConfirmPassword.setBounds(20, 100, 180, 50);
		view.add(ConfirmPassword);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(200, 60, 200, 30);
		view.add(passwordField);
		
		JPasswordField confirmField = new JPasswordField();
		confirmField.setBounds(200, 110, 200, 30);
		view.add(confirmField);
		
		JButton reset = new JButton("RESET PASSWORD");
		reset.setBounds(150, 160, 150, 30);
		view.add(reset);
		
		reset.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {        	   
            	  try {
            		  String password = passwordField.getText();
            		  MessageDigest m = MessageDigest.getInstance("MD5");
            		  m.update(password.getBytes());
                      
            		  
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/big_data", "root", "8452");
					PreparedStatement st = con.prepareStatement("update password from user where password =? ");
					st.setString(1, passwordField.getText());
					ResultSet rs = st.executeQuery();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			  });
				
		

	}

}
