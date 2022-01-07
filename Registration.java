package user;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Registration {

	public static JFrame page = new JFrame();

	public static void main(String[] a) {
      new Registration();
	}

	public Registration() {

		page.setSize(500, 850);
		page.getContentPane().setLayout(null);
		page.setVisible(true);
		page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		page.setResizable(true);
		page.setLocationRelativeTo(null);

		JLabel mandatory = new JLabel("Mandatory to fill * fields");
		mandatory.setBounds(155, 800, 150, 35);
		page.add(mandatory);

		JLabel name = new JLabel("NAME *");
		name.setBounds(20, 20, 70, 70);
		page.add(name);

		JLabel email = new JLabel("EMAIL *");
		email.setBounds(20, 80, 100, 70);
		page.add(email);

		JLabel username = new JLabel("USERNAME *");
		username.setBounds(20, 140, 100, 70);
		page.add(username);

		JLabel password = new JLabel("PASSWORD *");
		password.setBounds(20, 200, 140, 70);
		page.add(password);

		JLabel confirmpassword = new JLabel("CONFIRM PASSWORD *");
		confirmpassword.setBounds(20, 260, 140, 70);
		page.add(confirmpassword);

		JLabel city = new JLabel("CITY");
		city.setBounds(20, 320, 140, 70);
		page.add(city);

		JLabel question1 = new JLabel("SET YOUR 1st QUESTION *");
		question1.setBounds(20, 380, 170, 70);
		page.add(question1);

		JLabel answer1 = new JLabel("YOUR ANSWER *");
		answer1.setBounds(20, 440, 140, 70);
		page.add(answer1);

		JLabel question2 = new JLabel("SET YOUR 2nd QUESTION");
		question2.setBounds(20, 500, 170, 70);
		page.add(question2);

		JLabel answer2 = new JLabel("YOUR ANSWER");
		answer2.setBounds(20, 560, 140, 70);
		page.add(answer2);

		JLabel question3 = new JLabel("SET YOUR 3rd QUESTION");
		question3.setBounds(20, 620, 170, 70);
		page.add(question3);

		JLabel answer3 = new JLabel("YOUR ANSWER");
		answer3.setBounds(20, 680, 140, 70);
		page.add(answer3);

		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(180, 40, 165, 23);
		page.add(nameTextField);

		JTextField Emailtextfield = new JTextField();
		Emailtextfield.setBounds(180, 100, 165, 23);
		page.add(Emailtextfield);

		JTextField usernameTextfield = new JTextField();
		usernameTextfield.setBounds(180, 160, 165, 23);
		page.add(usernameTextfield);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(180, 220, 165, 23);
		page.add(passwordField);

		JPasswordField confirmpasswordField = new JPasswordField();
		confirmpasswordField.setBounds(180, 280, 165, 23);
		page.add(confirmpasswordField);

		JTextField cityField = new JTextField();
		cityField.setBounds(180, 340, 165, 23);
		page.add(cityField);

		JTextField questionField1 = new JTextField();
		questionField1.setBounds(180, 400, 165, 23);
		page.add(questionField1);

		JTextField answerField1 = new JTextField();
		answerField1.setBounds(180, 460, 165, 23);
		page.add(answerField1);

		JTextField questionField2 = new JTextField();
		questionField2.setBounds(180, 520, 165, 23);
		page.add(questionField2);

		JTextField answerField2 = new JTextField();
		answerField2.setBounds(180, 580, 165, 23);
		page.add(answerField2);

		JTextField questionField3 = new JTextField();
		questionField3.setBounds(180, 640, 165, 23);
		page.add(questionField3);

		JTextField answerField3 = new JTextField();
		answerField3.setBounds(180, 700, 165, 23);
		page.add(answerField3);

		JButton register = new JButton("Register");
		register.setBounds(70, 750, 100, 35);
		page.add(register);

		JButton login = new JButton("Login");
		login.setBounds(280, 750, 100, 35);
		page.add(login);

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (nameTextField.getText().trim().isBlank() | Emailtextfield.getText().trim().isEmpty()
						| usernameTextfield.getText().trim().isEmpty() | passwordField.getText().trim().isEmpty()
						| questionField1.getText().trim().isBlank() | answerField1.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(null, "Mandatory to fill * fields");
				} else {

					try {
						if (passwordField.getText().equals(confirmpasswordField.getText())) {

							String EncryptedPassword = Encryption.getEncrypt(passwordField.getText());

							String EncryptedAnswer1 = answerField1.getText().toLowerCase();
							EncryptedAnswer1 = Encryption.getEncrypt(EncryptedAnswer1);

							String EncryptedAnswer2 = answerField2.getText().toLowerCase();
							EncryptedAnswer2 = Encryption.getEncrypt(EncryptedAnswer2);

							String EncryptedAnswer3 = answerField3.getText().toLowerCase();
							EncryptedAnswer3 = Encryption.getEncrypt(EncryptedAnswer3);

							Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/big_data",
									"root", "8452");
							PreparedStatement pst = connect.prepareStatement(
									"insert into user (name ,Email, username, password, city, question1,answer1,question2,answer2,question3,answer3)"
											+ "values(?,?,?,?,?,?,?,?,?,?,?)");

							pst.setString(1, nameTextField.getText());
							pst.setString(2, Emailtextfield.getText());
							pst.setString(3, usernameTextfield.getText());
							pst.setString(4, EncryptedPassword);
							pst.setString(5, cityField.getText());
							pst.setString(6, questionField1.getText());
							pst.setString(7, EncryptedAnswer1);
							pst.setString(8, questionField2.getText());
							pst.setString(9, EncryptedAnswer2);
							pst.setString(10, questionField3.getText());
							pst.setString(11, EncryptedAnswer3);
							pst.executeUpdate();

							JOptionPane.showMessageDialog(null, "Hello" + " " + nameTextField.getText() + " "
									+ "Your Data Registered Sucessfully ");

							connect.close();

							// Erase Entered Data From Register Page
							nameTextField.setText("");
							Emailtextfield.setText("");
							usernameTextfield.setText("");
							passwordField.setText("");
							confirmpasswordField.setText("");
							cityField.setText("");
							questionField1.setText("");
							answerField1.setText("");
							questionField2.setText("");
							answerField2.setText("");
							questionField3.setText("");
							answerField3.setText("");

						} else {
							JOptionPane.showMessageDialog(null, "Password did not Match");
						}

					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new login();

			}
		});
	}

}