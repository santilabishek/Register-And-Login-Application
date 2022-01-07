package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.*;

public class ForgetPassword {
	public ForgetPassword() {
		
		JFrame forget = new JFrame("Enter The Details");
		forget.setBounds(10, 10, 450, 350);
		forget.getContentPane().setBackground(Color.cyan);
		forget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		forget.setLayout(null);
		forget.setResizable(false);
		forget.setVisible(true);

		JLabel Email = new JLabel("Enter Your Email ID:");
		Email.setBounds(20, 50, 150, 30);
		forget.add(Email);

		JTextField EmailField = new JTextField();
		EmailField.setBounds(180, 50, 200, 30);
		forget.add(EmailField);

		JButton verify = new JButton("VERIFY");
		verify.setBounds(150, 100, 100, 30);
		forget.add(verify);

		JTextField messageField = new JTextField();
		messageField.setBounds(180, 150, 200, 30);

		JButton submit = new JButton("SUBMIT");
		submit.setBounds(150, 200, 100, 30);

		verify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = EmailField.getText();

				try {
					Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/big_data", "root", "8452");
					PreparedStatement st = (PreparedStatement) c
							.prepareStatement("select Email,question1 from user where Email=?");
					st.setString(1, email);
					ResultSet rs = st.executeQuery();

					if (rs.next()) {

						JLabel message = new JLabel(rs.getString("question"));
						message.setBounds(20, 130, 200, 70);
						forget.add(message);
						forget.add(messageField);
						forget.add(submit);

						submit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {

									String Message = messageField.getText();
									MessageDigest m = MessageDigest.getInstance("MD5");
									m.update(Message.getBytes());
									byte[] bytes = m.digest();
									StringBuilder s = new StringBuilder();
									for (int i = 0; i < bytes.length; i++) {
										s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
									}
									String EncryptedMessage = s.toString();
									PreparedStatement smt = c
											.prepareStatement("select username , answer from user where answer=?");
									smt.setString(1, EncryptedMessage);
									ResultSet set = smt.executeQuery();

									if (set.next() == true) { 
										//new change();

									} else {
										JOptionPane.showMessageDialog(null, "Answer Dosen't Match!!! Please Try Again");
									}

								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}

						});

					} else {
						JOptionPane.showMessageDialog(null, "EMAIL DOES NOT EXIST");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

	}
}
