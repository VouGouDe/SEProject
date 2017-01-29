package com.se_project.manager;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.se_project.connection.ConnectionToMySQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateAccount extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5397254632796064758L;
	private JPanel contentPane;
	private JTextField userField;
	private JTextField passField;
	private JCheckBox adminField;
	private Connection myConnection = ConnectionToMySQL.getConnection();

	public CreateAccount() {
		setResizable(false);
		setTitle("Create Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblCreateAccount = new JLabel("Create Account",
				SwingConstants.CENTER);
		lblCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCreateAccount.setBounds(139, 11, 152, 38);
		contentPane.add(lblCreateAccount);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(118, 84, 75, 14);		
		contentPane.add(lblUsername);
		
		userField = new JTextField();
		userField.setBounds(203, 81, 120, 20);
		contentPane.add(userField);
		userField.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		userField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(118, 125, 75, 14);
		contentPane.add(lblPassword);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		passField.setBounds(203, 122, 120, 20);
		contentPane.add(passField);
		
		adminField = new JCheckBox("Administrative Account");
		adminField.setBounds(139, 163, 152, 23);
		adminField.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		adminField.setBorderPainted(true);
		contentPane.add(adminField);		
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accountCreation(userField.getText(),passField.getText());
			}
		});
		btnNewButton.setBounds(169, 207, 92, 23);
		contentPane.add(btnNewButton);
	}
	
	public void accountCreation(String user,String pass){
		try {
			int adminStatus=0;
			if(adminField.isSelected()){
				adminStatus=1;
			}
			Statement stmt = myConnection.createStatement();
			stmt.executeUpdate("INSERT INTO accounts VALUES ('"
					+ user + "','"
					+ pass + "',"
					+ adminStatus + ",0)");					
			JOptionPane.showMessageDialog(null,
					"Account created succesfully.");
		} catch (SQLException e) {
			String problem = e.getMessage();
			System.out.println(problem);
			JOptionPane.showMessageDialog(null,
					problem.substring(0, problem.length() - 18)
							+ ". Please give a valid entry.");
		}
	}
}