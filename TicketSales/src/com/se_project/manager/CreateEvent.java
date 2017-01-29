package com.se_project.manager;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

import com.se_project.connection.ConnectionToMySQL;

public class CreateEvent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7485573322065344903L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField artistField;
	private JTextField venueField;
	private JFormattedTextField dateField;
	private JTextField arenaTicketField;
	private JTextField arenaCostField;
	private static JTextField posterField;
	private JTextArea descriptionField;
	private Connection myConnection = ConnectionToMySQL.getConnection();
	private JTextField leftTicketField;
	private JTextField leftCostField;
	private JTextField rightCostField;
	private JTextField rightTicketField;

	public CreateEvent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Create Event");
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCreateEvent = new JLabel("Create Event",
				SwingConstants.CENTER);
		lblCreateEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCreateEvent.setBounds(213, 11, 152, 38);
		contentPane.add(lblCreateEvent);

		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblArtist.setBounds(31, 80, 46, 14);
		contentPane.add(lblArtist);

		artistField = new JTextField();
		artistField.setBounds(87, 78, 120, 20);
		artistField
				.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		contentPane.add(artistField);
		artistField.setColumns(10);

		JLabel label = new JLabel("Venue:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(31, 111, 50, 14);
		contentPane.add(label);

		venueField = new JTextField();
		venueField.setColumns(10);
		venueField.setBounds(87, 109, 120, 20);
		venueField.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		contentPane.add(venueField);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(31, 201, 50, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("(YYYY-MM-DD HH:MM:SS)");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(31, 226, 152, 14);
		contentPane.add(label_2);

		dateField = new JFormattedTextField(
				createFormatter("####-##-## ##:##:##"));
		dateField.setBounds(87, 199, 120, 20);
		dateField.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		contentPane.add(dateField);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(246, 373, 89, 23);
		contentPane.add(btnNewButton);

		arenaTicketField = new JTextField();
		arenaTicketField.setText("");
		arenaTicketField.setColumns(10);
		arenaTicketField.setBounds(424, 78, 120, 20);
		arenaTicketField.setBorder((BorderFactory.createLineBorder(
				new Color(0), 1)));
		contentPane.add(arenaTicketField);

		JLabel lblCost = new JLabel("Arena:");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCost.setBounds(368, 80, 46, 14);
		contentPane.add(lblCost);

		arenaCostField = new JTextField();
		arenaCostField.setText("");
		arenaCostField.setColumns(10);
		arenaCostField.setBounds(424, 109, 120, 20);
		arenaCostField.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		contentPane.add(arenaCostField);

		JLabel lblPoster = new JLabel("Poster:");
		lblPoster.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPoster.setBounds(31, 142, 46, 14);
		contentPane.add(lblPoster);

		posterField = new JTextField();
		posterField.setColumns(10);
		posterField.setBounds(87, 140, 120, 20);
		posterField
				.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		contentPane.add(posterField);

		JLabel lblinsertFullPath = new JLabel("(Insert full path)");
		lblinsertFullPath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblinsertFullPath.setBounds(31, 167, 104, 14);
		contentPane.add(lblinsertFullPath);

		JLabel label_3 = new JLabel("Description:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(31, 255, 68, 14);
		contentPane.add(label_3);

		descriptionField = new JTextArea();
		descriptionField.setText("Insert Description...");
		descriptionField.setWrapStyleWord(true);
		descriptionField.setBounds(31, 280, 513, 60);
		descriptionField.setEditable(true);
		descriptionField.setLineWrap(true);
		descriptionField.setBorder((BorderFactory.createLineBorder(
				new Color(0), 1)));
		contentPane.add(descriptionField);

		leftTicketField = new JTextField();
		leftTicketField.setText("0");
		leftTicketField.setColumns(10);
		leftTicketField.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		leftTicketField.setBounds(424, 140, 120, 20);
		contentPane.add(leftTicketField);

		JLabel lblTicketseated = new JLabel("Cost:");
		lblTicketseated.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTicketseated.setBounds(378, 111, 36, 14);
		contentPane.add(lblTicketseated);

		JLabel lblSeatedLeft = new JLabel("Seated Left:");
		lblSeatedLeft.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSeatedLeft.setBounds(338, 142, 76, 14);
		contentPane.add(lblSeatedLeft);

		JLabel label_4 = new JLabel("Cost:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBounds(378, 173, 36, 14);
		contentPane.add(label_4);

		leftCostField = new JTextField();
		leftCostField.setText("0");
		leftCostField.setColumns(10);
		leftCostField.setBorder((BorderFactory
				.createLineBorder(new Color(0), 1)));
		leftCostField.setBounds(424, 171, 120, 20);
		contentPane.add(leftCostField);

		rightCostField = new JTextField();
		rightCostField.setText("0");
		rightCostField.setColumns(10);
		rightCostField.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		rightCostField.setBounds(424, 232, 120, 20);
		contentPane.add(rightCostField);

		rightTicketField = new JTextField();
		rightTicketField.setText("0");
		rightTicketField.setColumns(10);
		rightTicketField.setBorder((BorderFactory.createLineBorder(
				new Color(0), 1)));
		rightTicketField.setBounds(424, 201, 120, 20);
		contentPane.add(rightTicketField);

		JLabel label_5 = new JLabel("Cost:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_5.setBounds(378, 234, 36, 14);
		contentPane.add(label_5);

		JLabel lblSeatedRight = new JLabel("Seated Right:");
		lblSeatedRight.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSeatedRight.setBounds(330, 201, 84, 14);
		contentPane.add(lblSeatedRight);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventCreation();
			}
		});

	}

	private MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			JOptionPane.showMessageDialog(null, "Error during table creation.");
			System.exit(-1);
		}
		return formatter;
	}

	public void eventCreation() {
		String eventChecker = checkEvent();
		if (!eventChecker.equals("check ok")) {
			JOptionPane.showMessageDialog(null, eventChecker);
		} else {
			FileInputStream fis = null;
			PreparedStatement ps = null;
			String INSERT_PICTURE = "INSERT INTO event VALUES(?,?,?,?,?)";
			try {

				Statement stmt = myConnection.createStatement();
				myConnection.setAutoCommit(false);
				File file = new File(posterField.getText());
				fis = new FileInputStream(file);
				ps = myConnection.prepareStatement(INSERT_PICTURE);
				ps.setString(1, artistField.getText());
				ps.setBinaryStream(2, fis, (int) file.length());
				ps.setString(3, venueField.getText());
				ps.setString(4, dateField.getText());
				ps.setString(5, descriptionField.getText());
				ps.executeUpdate();
				stmt.executeUpdate("INSERT INTO eventspecifics VALUES ('"
						+ artistField.getText() + "',"
						+ Integer.parseInt(arenaTicketField.getText()) + ",0,"
						+ Float.parseFloat(arenaCostField.getText()) + ","
						+ Integer.parseInt(leftTicketField.getText()) + ",0,"
						+ Float.parseFloat(leftCostField.getText()) + ","
						+ Integer.parseInt(rightTicketField.getText()) + ",0,"
						+ Float.parseFloat(rightCostField.getText()) + ")");
				stmt.executeUpdate("INSERT INTO eventbasics VALUES ('"
						+ artistField.getText() + "','" + dateField.getText()
						+ "','" + venueField.getText() + "',"
						+ Float.parseFloat(arenaCostField.getText()) + ")");
				myConnection.commit();
				JOptionPane.showMessageDialog(null,
						"Event created succesfully."
								+ "Please log back in to see updated events.");
				fis.close();
			} catch (SQLException e) {
				String problem = e.getMessage();
				JOptionPane.showMessageDialog(null,
						problem.substring(0, problem.length() - 18)
								+ ". Please give a valid entry.");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"File was not found. Please enter a valid path.");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Problem while closing connection.");
			}
		}
	}

	private String checkEvent() {
		if (artistField.getText().equals("")) {
			return "Please enter an artist.";
		}
		if (venueField.getText().equals("")) {
			return "Please enter a venue.";
		}

		File image = new File(posterField.getText());
		try {
			FileInputStream fis = new FileInputStream(image);
			fis.close();
		} catch (FileNotFoundException e) {
			return "File was not found. Please enter a valid path.";
		} catch (IOException e) {
			return "Problem while reading file";
		}

		String myText = dateField.getText();
		if (myText.equals("    -  -     :  :  ")) {
			return "Please enter a date.";
		} else {
			int yearCheck = Integer.parseInt(myText.substring(0, 4));
			int monthCheck = Integer.parseInt(myText.substring(5, 7));
			int dayCheck = Integer.parseInt(myText.substring(8, 10));
			int hourCheck = Integer.parseInt(myText.substring(11, 13));
			int minCheck = Integer.parseInt(myText.substring(14, 16));
			int secCheck = Integer.parseInt(myText.substring(17, 19));
			if (!(yearCheck <= 2050 && yearCheck >= 2017 && monthCheck <= 12
					&& dayCheck <= 31 && hourCheck <= 23 && minCheck <= 59 && secCheck <= 59)) {
				return "Please enter a valid date.";
			}
		}

		if (arenaTicketField.getText().equals("")
				|| arenaCostField.getText().equals("")) {
			return "Please enter at least arena tickets and costs.";
		}
		if (arenaTicketField.getText().matches(".*[a-z].*")
				|| rightTicketField.getText().matches(".*[a-z].*")
				|| leftTicketField.getText().matches(".*[a-z].*")) {
			return "Please enter a valid ticket number.";
		}
		if (arenaCostField.getText().matches(".*[a-z].*")
				|| rightCostField.getText().matches(".*[a-z].*")
				|| leftCostField.getText().matches(".*[a-z].*")) {
			return "Please enter a valid cost number.";
		}
		return "check ok";
	}

}
