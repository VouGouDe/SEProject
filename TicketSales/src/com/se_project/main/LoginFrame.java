package com.se_project.main;

import java.awt.EventQueue;




import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import com.se_project.connection.ConnectionToMySQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * The login frame that comes up when you first open the application. 
 * Reads the input data, contacts the database,
 * tries to make a connection and match the data to the "accounts" table.
 * When successful, disposes the frame and opens up the main frame.
* */
public class LoginFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5366064334443531399L;
	
	/**
	 * The login frame.
	 */
	private static LoginFrame frame;
	
	/**
	 * The main contentPane holder.
	 */
	transient final private JPanel contentPane;
	
	/**
	 * User input text field.
	 */	
	transient final private JTextField textField1;
	
	/**
	 * Password input text field.
	 */
	transient final private JPasswordField passwordField;
	
	/**
	 * "User" label.
	 */
	transient final private JLabel lblNewLabel1;
	
	/**
	 * "Password" label.
	 */
	transient final private JLabel lblNewLabel2;
	
	/**
	 * "Login" button. Makes connection with database.
	 */
	transient final private JButton btnNewButton;
	
	/**
	 * Holds the currently logged-in user.
	 */
	private static String user;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {

			/**
			 * Tries to initiate a thread with the Login Frame.
			 */
			public void run() {
				try {
					frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Problem Initiating Application.");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		super();
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(200, 66, 80, 33);
		contentPane.add(lblNewLabel);

		lblNewLabel1 = new JLabel("User");
		lblNewLabel1.setBounds(161, 147, 46, 14);
		contentPane.add(lblNewLabel1);

		textField1 = new JTextField();
		textField1.setBounds(217, 144, 120, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);

		lblNewLabel2 = new JLabel("Password");
		lblNewLabel2.setBounds(161, 178, 46, 14);
		contentPane.add(lblNewLabel2);

		passwordField = new JPasswordField();
		passwordField.setBounds(217, 175, 120, 20);
		contentPane.add(passwordField);

		
		btnNewButton = new JButton("Login");
		contentPane.getRootPane().setDefaultButton(btnNewButton);
		/**
		 * Calls the startApp() function.
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				startApp();
			}
		});
		btnNewButton.setBounds(300, 238, 89, 23);
		contentPane.add(btnNewButton);

	}
	
	/**
	 * Returns the current logged-in user.
	 */
	public static String getUser(){
		return user;
	}
	
	/**
	 * Disposes the current frame and opens up a new login frame.
	 */
	public static void closeApp(){
		frame.dispose();
		final LoginFrame newFrame = new LoginFrame();
		newFrame.setVisible(true);
	}
	/**
	 * Creates a connection with the server, 
	 * checking the login values to be correct. If successful, 
	 * disposes the current frame and opens up the main page.
	 **/
	public void startApp(){
		final String userName= textField1.getText();
		final String pass= String.valueOf(passwordField.getPassword());
		final ConnectionToMySQL newConnection = new ConnectionToMySQL(userName,pass);
		if(newConnection.SuccessfullLogin()){
			user=userName;
			dispose();					
			final MainPage mainFrame = new MainPage();
			mainFrame.setVisible(true);
		}
	}
}
