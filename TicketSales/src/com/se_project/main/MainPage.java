package com.se_project.main;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JTextArea;

import com.se_project.connection.ConnectionToMySQL;
import com.se_project.manager.CreateAccount;
import com.se_project.manager.CreateEvent;
import com.se_project.manager.DeleteAccount;
import com.se_project.manager.DeleteEvent;
import com.se_project.manager.GraphMaker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1250941060076880588L;
	private JPanel contentPane;
	private JTextField artistField;
	private JTextField txtSearchHere;
	private int eventCounter = 0;
	private JLabel newLabel;
	private JPanel leftPanel;
	private JLabel posterHolder;
	private JTextArea descriptionField;
	private Connection myConnection = ConnectionToMySQL.getConnection();

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setResizable(false);
		setTitle("Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);


		String fieldDummieText = "Search here...";
		txtSearchHere = new JTextField(fieldDummieText);
		txtSearchHere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				checkText();
			}
		});
		menuBar.add(txtSearchHere);
		txtSearchHere.setColumns(10);

		JMenu mnNewMenu_1 = new JMenu("Manage");
		menuBar.add(mnNewMenu_1);
		if (ConnectionToMySQL.getStatus() == 1) {
			mnNewMenu_1.setEnabled(true);
		} else {
			mnNewMenu_1.setEnabled(false);
		}

		JMenu mnAccounts = new JMenu("Accounts");
		mnNewMenu_1.add(mnAccounts);

		JMenuItem mntmCreateAccount = new JMenuItem("Create Account");
		mntmCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccount newAccount = new CreateAccount();
				newAccount.setVisible(true);
			}
		});
		mnAccounts.add(mntmCreateAccount);

		JMenuItem mntmDeleteAccount = new JMenuItem("Delete Account");
		mntmDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAccount deleteAccount = new DeleteAccount();
				deleteAccount.setVisible(true);
			}
		});
		mnAccounts.add(mntmDeleteAccount);
		
		JMenu mnEvents = new JMenu("Events");
		mnNewMenu_1.add(mnEvents);

		JMenuItem mntmAddEvent = new JMenuItem("Add Event");
		mnEvents.add(mntmAddEvent);

		JMenuItem mntmDeleteEvent = new JMenuItem("Delete Event");
		mnEvents.add(mntmDeleteEvent);

		JMenuItem mntmGraphs = new JMenuItem("Statistics");
		mnNewMenu_1.add(mntmGraphs);
		mntmGraphs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphMaker graph = new GraphMaker();
				graph.setVisible(true);
			}
		});
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		menuBar.add(mntmLogout);
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		artistField = new JTextField();
		artistField.setEditable(false);
		artistField
				.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		artistField.setBounds(149, 40, 155, 20);

		leftPanel = new JPanel();
		leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		leftPanel.setLayout(new FlowLayout(10, 10, 10));
		leftPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JScrollPane scrollPane = new JScrollPane(leftPanel);
		scrollPane.setBounds(new Rectangle(5, 5, 530, 631));
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(scrollPane);

		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(525, 5, 435, 631);
		rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		rightPanel.add(artistField);
		artistField.setColumns(10);

		posterHolder = new JLabel();
		posterHolder.setBounds(96, 83, 270, 355);
		rightPanel.add(posterHolder);

		descriptionField = new JTextArea();
		descriptionField.setWrapStyleWord(true);
		descriptionField.setBounds(83, 469, 300, 73);
		descriptionField.setEditable(false);
		descriptionField.setLineWrap(true);
		descriptionField.setBorder((BorderFactory.createLineBorder(
				new Color(0), 1)));
		rightPanel.add(descriptionField);

		JButton btnNewButton_1 = new JButton("Order Now");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TicketSelection newOrder = new TicketSelection();
				newOrder.initBoxes(artistField.getText());
				newOrder.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(175, 565, 111, 40);
		rightPanel.add(btnNewButton_1);

		getEvents();

		mntmDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteEvent endEvent = new DeleteEvent();
				endEvent.setVisible(true);
				getEvents();
			}
		});

		mntmAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateEvent newEvent = new CreateEvent();
				newEvent.setVisible(true);
				getEvents();
			}
		});
	}

	public void getEvents() {
		leftPanel.removeAll();
		try {
			Statement stmt = myConnection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM event INNER JOIN eventbasics ON event.eventArtist=eventbasics.eventArtist");
			while (rs.next()) {
				eventCounter++;
				byte[] img = rs.getBytes("eventPoster");
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				Image myImg = im
						.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				newLabel = new JLabel(newImage);
				newLabel.setText("<html>Artist: " + rs.getString("eventArtist")
						+ "<br>Cost: " + rs.getFloat("eventCost")
						+ "€<br>Date :" + rs.getString("eventDate")
						+ "<br>Venue: " + rs.getString("eventVenue")
						+ "</html>");
				newLabel.setHorizontalTextPosition(JLabel.CENTER);
				newLabel.setVerticalTextPosition(JLabel.BOTTOM);
				newLabel.setBorder(BorderFactory.createLineBorder(new Color(0),
						2));
				String artist = rs.getString("eventArtist");
				String description = rs.getString("eventDescription");
				Image largeImg = im.getScaledInstance(270, 355,
						Image.SCALE_SMOOTH);
				ImageIcon newLargeImage = new ImageIcon(largeImg);
				newLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						posterHolder.setIcon(newLargeImage);
						artistField.setText(artist);
						descriptionField.setText(description);
					}
				});
				leftPanel.add(newLabel);
			}		
			eventCounter++;
			leftPanel.setPreferredSize(new Dimension(500, 100 * eventCounter));
			leftPanel.revalidate();
			leftPanel.repaint();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problem contacting database.");
		}
	}
	
	public void checkText(){
		for (int i = 0; i < eventCounter - 1; i++) {
			if (leftPanel.getComponent(i) instanceof JLabel) {
				JLabel label = (JLabel) leftPanel.getComponent(i);
				if ((label.getText().toLowerCase())
						.contains((txtSearchHere.getText()
								.toLowerCase()))
						&& (txtSearchHere.getText() != " ")) {
					label.setFont(new Font("Serif", Font.BOLD, 12));
					label.setBorder(BorderFactory.createLineBorder(
							new Color(255, 0, 0), 2));
					label.revalidate();
					label.repaint();
				} else {
					label.setBorder(BorderFactory.createLineBorder(
							new Color(0), 2));
					label.setFont(new Font("Dialog", Font.BOLD, 12));
					label.revalidate();
					label.repaint();
				}
			}
		}
	}
	
	public void logout(){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane
				.showConfirmDialog(
						null,
						"Are you sure you want to logout?"
								,
						"Warning", dialogButton);
		if (dialogResult == 0) {
			dispose();
			LoginFrame newFrame = new LoginFrame();
			newFrame.setVisible(true);
		}
	}
}