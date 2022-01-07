package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.*;

public class login {

	login() {
		JFrame frame = new JFrame();

		frame.setTitle("User Login");
		frame.setVisible(true);
		frame.setBounds(10, 10, 370, 500);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);

		JLabel userLabel = new JLabel("USERNAME");
		userLabel.setBounds(50, 150, 100, 30);
		frame.add(userLabel);

		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setBounds(50, 220, 100, 30);
		frame.add(passwordLabel);

		JTextField userTextField = new JTextField();
		userTextField.setBounds(150, 150, 150, 30);
		frame.add(userTextField);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(150, 220, 150, 30);
		frame.add(passwordField);

		JButton registerButton = new JButton("REGISTER");
		registerButton.setBounds(200, 300, 100, 30);
		frame.add(registerButton);

		JButton loginButton = new JButton("LOGIN");
		loginButton.setBounds(50, 300, 100, 30);
		frame.add(loginButton);

		JCheckBox showPassword = new JCheckBox("Show Password");
		showPassword.setBounds(150, 250, 150, 30);
		frame.add(showPassword);

		JButton ForgetPassword = new JButton("Forget Password");
		ForgetPassword.setBounds(100, 350, 150, 30);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userTextField.getText();
				String password = passwordField.getText();
				try {
					MessageDigest m = MessageDigest.getInstance("MD5");
					m.update(password.getBytes());
					byte[] bytes = m.digest();
					StringBuilder s = new StringBuilder();
					for (int i = 0; i < bytes.length; i++) {
						s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
					}
					password = s.toString();
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/big_data", "root",
							"8452");
					PreparedStatement st = (PreparedStatement) con
							.prepareStatement("Select name, username, password from user where username=? and password=?");
					st.setString(1, userName);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					
					// Remove Information From Login Page
					userTextField.setText("");
					passwordField.setText("");

					if (rs.next() == true) {
						JOptionPane.showMessageDialog(null, "Welcome" +" "+rs.getString("name") +" " + "You have successfully logged in");
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Username & Password");
						frame.add(ForgetPassword);
					}
					con.close();

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration();
			}

		});
		showPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPassword.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		ForgetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgetPassword();
			}
		});
	}
}
