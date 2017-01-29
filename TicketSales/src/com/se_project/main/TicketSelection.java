package com.se_project.main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.se_project.connection.ConnectionToMySQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketSelection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7347423081737329682L;
	private JPanel contentPane;
	private JLabel arenaLabel;
	private JLabel leftLabel;
	private JLabel rightLabel;
	private JTextField leftTotal;
	private JTextField leftAvailable;
	private JTextField leftOrder;
	private JTextField leftPrice;
	private JTextField arenaTotal;
	private JTextField arenaAvailable;
	private JTextField arenaPrice;
	private JTextField arenaOrder;
	private JTextField rightTotal;
	private JTextField rightAvailable;
	private JTextField rightPrice;
	private JTextField rightOrder;
	private JButton arenaMinus;
	private JButton arenaPlus;
	private JButton leftMinus;
	private JButton leftPlus;
	private JButton rightMinus;
	private JButton rightPlus;
	private JButton checkOut;
	private Connection myConnection = ConnectionToMySQL.getConnection();
	private JTextField leftOrderPrice;
	private JTextField arenaOrderPrice;
	private JTextField rightOrderPrice;
	private String event = "";

	public TicketSelection() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Book Tickets");
		setBounds(100, 100, 530, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel stageLabel = new JLabel("STAGE", SwingConstants.CENTER);
		stageLabel.setBackground(Color.WHITE);
		stageLabel.setOpaque(true);
		stageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stageLabel.setBorder((BorderFactory.createLineBorder(new Color(0), 2)));
		stageLabel.setBounds(189, 25, 142, 34);
		contentPane.add(stageLabel);

		arenaLabel = new JLabel("ARENA", SwingConstants.CENTER);
		arenaLabel.setBackground(Color.WHITE);
		arenaLabel.setOpaque(true);
		arenaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		arenaLabel.setBorder((BorderFactory.createLineBorder(new Color(0), 2)));
		arenaLabel.setBounds(189, 70, 142, 173);
		contentPane.add(arenaLabel);

		rightLabel = new JLabel("<html>RIGHT<br>SIDE</hmtl>",
				SwingConstants.CENTER);
		rightLabel.setBackground(Color.WHITE);
		rightLabel.setOpaque(true);
		rightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rightLabel.setBorder((BorderFactory.createLineBorder(new Color(0), 2)));
		rightLabel.setBounds(341, 70, 67, 129);
		contentPane.add(rightLabel);

		leftLabel = new JLabel("<html>LEFT<br>SIDE</html>",
				SwingConstants.CENTER);
		leftLabel.setBackground(Color.WHITE);
		leftLabel.setOpaque(true);
		leftLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		leftLabel.setBorder((BorderFactory.createLineBorder(new Color(0), 2)));
		leftLabel.setBounds(112, 70, 67, 129);
		contentPane.add(leftLabel);

		JLabel lblLeftTickets = new JLabel("Left Side Tickets",
				SwingConstants.CENTER);
		lblLeftTickets.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		lblLeftTickets.setBackground(Color.WHITE);
		lblLeftTickets.setOpaque(true);
		lblLeftTickets.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLeftTickets.setBounds(78, 261, 106, 34);
		contentPane.add(lblLeftTickets);

		JLabel lblAvailable = new JLabel("Total:");
		lblAvailable.setBounds(78, 309, 47, 14);
		contentPane.add(lblAvailable);

		leftTotal = new JTextField();
		leftTotal.setEditable(false);
		leftTotal.setBounds(135, 306, 49, 19);
		leftTotal.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		contentPane.add(leftTotal);
		leftTotal.setColumns(10);

		JLabel lblAvailable_1 = new JLabel("Available:");
		lblAvailable_1.setBounds(78, 334, 54, 14);
		contentPane.add(lblAvailable_1);

		leftAvailable = new JTextField();
		leftAvailable.setEditable(false);
		leftAvailable.setColumns(10);
		leftAvailable.setBorder((BorderFactory
				.createLineBorder(new Color(0), 1)));
		leftAvailable.setBounds(135, 331, 49, 19);
		contentPane.add(leftAvailable);

		leftPlus = new JButton("+");
		leftPlus.addActionListener(new ButtonListener());
		leftPlus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		leftPlus.setBounds(107, 384, 47, 19);
		contentPane.add(leftPlus);

		leftMinus = new JButton("-");
		leftMinus.addActionListener(new ButtonListener());
		leftMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		leftMinus.setBounds(107, 435, 47, 19);
		contentPane.add(leftMinus);

		leftOrder = new JTextField();
		leftOrder.setText("0");
		leftOrder.setColumns(10);
		leftOrder.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		leftOrder.setBounds(79, 409, 47, 19);
		contentPane.add(leftOrder);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(78, 359, 47, 14);
		contentPane.add(lblPrice);

		leftPrice = new JTextField();
		leftPrice.setEditable(false);
		leftPrice.setColumns(10);
		leftPrice.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		leftPrice.setBounds(135, 356, 49, 19);
		contentPane.add(leftPrice);

		JLabel lblArenaTickets = new JLabel("Arena Tickets",
				SwingConstants.CENTER);
		lblArenaTickets.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblArenaTickets.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		lblArenaTickets.setBackground(Color.WHITE);
		lblArenaTickets.setOpaque(true);
		lblArenaTickets.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		lblArenaTickets.setBounds(209, 261, 106, 34);
		contentPane.add(lblArenaTickets);

		JLabel label = new JLabel("Total:");
		label.setBounds(209, 309, 47, 14);
		contentPane.add(label);

		arenaTotal = new JTextField();
		arenaTotal.setEditable(false);
		arenaTotal.setColumns(10);
		arenaTotal.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		arenaTotal.setBounds(266, 306, 49, 19);
		contentPane.add(arenaTotal);

		arenaAvailable = new JTextField();
		arenaAvailable.setEditable(false);
		arenaAvailable.setColumns(10);
		arenaAvailable.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		arenaAvailable.setBounds(266, 331, 49, 19);
		contentPane.add(arenaAvailable);

		JLabel label_1 = new JLabel("Available:");
		label_1.setBounds(209, 334, 54, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Price:");
		label_2.setBounds(209, 359, 47, 14);
		contentPane.add(label_2);

		arenaPrice = new JTextField();
		arenaPrice.setEditable(false);
		arenaPrice.setColumns(10);
		arenaPrice.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		arenaPrice.setBounds(266, 356, 49, 19);
		contentPane.add(arenaPrice);

		arenaPlus = new JButton("+");
		arenaPlus.addActionListener(new ButtonListener());
		arenaPlus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		arenaPlus.setBounds(237, 383, 47, 19);
		contentPane.add(arenaPlus);

		arenaOrder = new JTextField();
		arenaOrder.setText("0");
		arenaOrder.setColumns(10);
		arenaOrder.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		arenaOrder.setBounds(210, 409, 47, 19);
		contentPane.add(arenaOrder);

		arenaMinus = new JButton("-");
		arenaMinus.addActionListener(new ButtonListener());
		arenaMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		arenaMinus.setBounds(237, 434, 47, 19);
		contentPane.add(arenaMinus);

		leftOrderPrice = new JTextField();
		leftOrderPrice.setText("0");
		leftOrderPrice.setEditable(false);
		leftOrderPrice.setBounds(135, 409, 47, 19);
		contentPane.add(leftOrderPrice);
		leftOrderPrice.setColumns(10);
		leftOrderPrice.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.LIGHT_GRAY);
		leftPanel.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		leftPanel.setBounds(70, 257, 120, 202);
		contentPane.add(leftPanel);

		arenaOrderPrice = new JTextField();
		arenaOrderPrice.setText("0");
		arenaOrderPrice.setEditable(false);
		arenaOrderPrice.setBounds(266, 409, 47, 19);
		contentPane.add(arenaOrderPrice);
		arenaOrderPrice.setColumns(10);
		arenaOrderPrice.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));

		JPanel arenaPanel = new JPanel();
		arenaPanel.setBackground(Color.LIGHT_GRAY);
		arenaPanel.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		arenaPanel.setBounds(201, 257, 120, 202);
		contentPane.add(arenaPanel);

		JLabel lblRightTickets = new JLabel("Right Side Tickets",
				SwingConstants.CENTER);
		lblRightTickets.setOpaque(true);
		lblRightTickets.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRightTickets.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		lblRightTickets.setBackground(Color.WHITE);
		lblRightTickets.setBounds(339, 261, 106, 34);
		contentPane.add(lblRightTickets);

		rightTotal = new JTextField();
		rightTotal.setEditable(false);
		rightTotal.setColumns(10);
		rightTotal.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		rightTotal.setBounds(396, 307, 49, 19);
		contentPane.add(rightTotal);

		JLabel label_4 = new JLabel("Total:");
		label_4.setBounds(339, 309, 47, 14);
		contentPane.add(label_4);

		rightAvailable = new JTextField();
		rightAvailable.setEditable(false);
		rightAvailable.setColumns(10);
		rightAvailable.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));
		rightAvailable.setBounds(396, 331, 49, 19);
		contentPane.add(rightAvailable);

		JLabel label_5 = new JLabel("Available:");
		label_5.setBounds(339, 334, 54, 14);
		contentPane.add(label_5);

		rightPrice = new JTextField();
		rightPrice.setEditable(false);
		rightPrice.setColumns(10);
		rightPrice.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		rightPrice.setBounds(396, 356, 49, 19);
		contentPane.add(rightPrice);

		JLabel label_6 = new JLabel("Price:");
		label_6.setBounds(339, 359, 47, 14);
		contentPane.add(label_6);

		rightPlus = new JButton("+");
		rightPlus.addActionListener(new ButtonListener());
		rightPlus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rightPlus.setBounds(366, 383, 47, 19);
		contentPane.add(rightPlus);

		rightOrder = new JTextField();
		rightOrder.setText("0");
		rightOrder.setColumns(10);
		rightOrder.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		rightOrder.setBounds(341, 409, 47, 19);
		contentPane.add(rightOrder);

		rightMinus = new JButton("-");
		rightMinus.addActionListener(new ButtonListener());
		rightMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rightMinus.setBounds(366, 434, 47, 19);
		contentPane.add(rightMinus);

		rightOrderPrice = new JTextField();
		rightOrderPrice.setText("0");
		rightOrderPrice.setEditable(false);
		rightOrderPrice.setBounds(397, 409, 47, 19);
		contentPane.add(rightOrderPrice);
		rightOrderPrice.setColumns(10);
		rightOrderPrice.setBorder((BorderFactory.createLineBorder(new Color(0),
				1)));

		JPanel panel = new JPanel();
		panel.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(332, 257, 120, 202);
		contentPane.add(panel);

		checkOut = new JButton("Check Out");
		checkOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ticketTransaction();
			}

		});
		checkOut.setBounds(161, 475, 190, 41);
		contentPane.add(checkOut);

	}

	public void initBoxes(String eventName) {
		event = eventName;
		try {
			Statement stmt = myConnection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM eventspecifics WHERE eventArtist='"
							+ eventName + "'");
			while (rs.next()) {
				int totalLeftTickets = (Integer.parseInt(rs
						.getString("eventLeftRemain")) + (Integer.parseInt(rs
						.getString("eventLeftSold"))));
				leftTotal.setText(String.valueOf(totalLeftTickets));
				leftAvailable.setText(rs.getString("eventLeftRemain"));
				leftPrice.setText(rs.getString("eventLeftCost"));
				if (rs.getInt("eventLeftRemain") == 0) {
					leftPlus.setEnabled(false);
					leftMinus.setEnabled(false);
					leftOrder.setEditable(false);
					leftLabel.setBorder(BorderFactory.createLineBorder(
							new Color(255, 0, 0), 2));
					leftLabel.revalidate();
					leftLabel.repaint();
				} else if (rs.getInt("eventLeftRemain") <= totalLeftTickets * 0.3) {
					leftLabel.setBorder(BorderFactory.createLineBorder(
							new Color(255, 140, 0), 2));
				} else {
					leftLabel.setBorder(BorderFactory.createLineBorder(
							new Color(0, 255, 0), 2));
				}
				int totalArenaTickets = (Integer.parseInt(rs
						.getString("eventArenaRemain")) + (Integer.parseInt(rs
						.getString("eventArenaSold"))));
				arenaTotal.setText(String.valueOf(totalArenaTickets));
				arenaAvailable.setText(rs.getString("eventArenaRemain"));
				arenaPrice.setText(rs.getString("eventArenaCost"));
				if (rs.getInt("eventArenaRemain") == 0) {
					arenaPlus.setEnabled(false);
					arenaMinus.setEnabled(false);
					arenaOrder.setEditable(false);
					arenaLabel.setBorder(BorderFactory.createLineBorder(
							new Color(255, 0, 0), 2));
					arenaLabel.revalidate();
					arenaLabel.repaint();
				} else if (rs.getInt("eventArenaRemain") <= totalArenaTickets * 0.3) {
					arenaLabel.setBorder(BorderFactory.createLineBorder(
							new Color(255, 140, 0), 2));
				} else {
					arenaLabel.setBorder(BorderFactory.createLineBorder(
							new Color(0, 255, 0), 2));
				}
				int totalRightTickets = (Integer.parseInt(rs
						.getString("eventRightRemain")) + (Integer.parseInt(rs
						.getString("eventRightSold"))));
				rightTotal.setText(String.valueOf(totalRightTickets));
				rightAvailable.setText(rs.getString("eventRightRemain"));
				rightPrice.setText(rs.getString("eventRightCost"));
				if (rs.getInt("eventRightRemain") == 0) {
					rightPlus.setEnabled(false);
					rightMinus.setEnabled(false);
					rightOrder.setEditable(false);
					rightLabel.setBorder(BorderFactory.createLineBorder(
							new Color(255, 0, 0), 2));
					rightLabel.revalidate();
					rightLabel.repaint();
				} else if (rs.getInt("eventRightRemain") <= totalRightTickets * 0.3) {
					rightLabel.setBorder(BorderFactory.createLineBorder(
							new Color(255, 140, 0), 2));
				} else {
					rightLabel.setBorder(BorderFactory.createLineBorder(
							new Color(0, 255, 0), 2));
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problem contacting database.");
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object buttonClicked = e.getSource();
			int tickets = 0;
			if (buttonClicked == leftPlus) {
				tickets = Integer.parseInt(leftOrder.getText());
				tickets++;
				leftOrderPrice.setText(String.valueOf(tickets
						* Float.parseFloat(leftPrice.getText())));
				leftOrderPrice.revalidate();
				leftOrderPrice.repaint();
				leftOrder.setText(String.valueOf(tickets));
			}
			if (buttonClicked == arenaPlus) {
				tickets = Integer.parseInt(arenaOrder.getText());
				tickets++;
				arenaOrderPrice.setText(String.valueOf(tickets
						* Float.parseFloat(arenaPrice.getText())));
				arenaOrderPrice.revalidate();
				arenaOrderPrice.repaint();
				arenaOrder.setText(String.valueOf(tickets));
			}
			if (buttonClicked == rightPlus) {
				tickets = Integer.parseInt(rightOrder.getText());
				tickets++;
				rightOrderPrice.setText(String.valueOf(tickets
						* Float.parseFloat(rightPrice.getText())));
				rightOrderPrice.revalidate();
				rightOrderPrice.repaint();
				rightOrder.setText(String.valueOf(tickets));
			}

			if (buttonClicked == leftMinus
					&& Integer.parseInt(leftOrder.getText()) > 0) {
				tickets = Integer.parseInt(leftOrder.getText());
				tickets--;
				leftOrderPrice.setText(String.valueOf(tickets
						* Float.parseFloat(leftPrice.getText())));
				leftOrderPrice.revalidate();
				leftOrderPrice.repaint();
				leftOrder.setText(String.valueOf(tickets));
			}
			if (buttonClicked == arenaMinus
					&& Integer.parseInt(arenaOrder.getText()) > 0) {
				tickets = Integer.parseInt(arenaOrder.getText());
				tickets--;
				arenaOrderPrice.setText(String.valueOf(tickets
						* Float.parseFloat(arenaPrice.getText())));
				arenaOrderPrice.revalidate();
				arenaOrderPrice.repaint();
				arenaOrder.setText(String.valueOf(tickets));
			}
			if (buttonClicked == rightMinus
					&& Integer.parseInt(rightOrder.getText()) > 0) {
				tickets = Integer.parseInt(rightOrder.getText());
				tickets--;
				rightOrderPrice.setText(String.valueOf(tickets
						* Float.parseFloat(rightPrice.getText())));
				rightOrderPrice.revalidate();
				rightOrderPrice.repaint();
				rightOrder.setText(String.valueOf(tickets));
			}
		}
	}

	public void ticketTransaction() {
		if (!(Integer.parseInt(arenaOrder.getText()) == 0
				&& Integer.parseInt(leftOrder.getText()) == 0 && Integer
					.parseInt(rightOrder.getText()) == 0)) {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(
					null,
					"<html><h1>Purchase</h1><br><h2>Arena:</h2>Tickets: "
							+ arenaOrder.getText() + "<br>Price: "
							+ arenaOrderPrice.getText()
							+ "€<br><h2>Left:</h2>Tickets: "
							+ leftOrder.getText() + "<br>Price: "
							+ leftOrderPrice.getText()
							+ "€<br><h2>Right:</h2>Tickets: "
							+ rightOrder.getText() + "<br>Price: "
							+ rightOrderPrice.getText() + "€<br></html>",
					"Check Out", dialogButton);
			if (dialogResult == 0) {
				Statement stmt;
				int arenaRemain = 0;
				int arenaSold = 0;
				int leftRemain = 0;
				int leftSold = 0;
				int rightRemain = 0;
				int rightSold = 0;
				try {
					stmt = myConnection.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT * FROM eventspecifics WHERE eventArtist='"
									+ event + "'");
					while (rs.next()) {
						arenaRemain = rs.getInt("eventArenaRemain");
						leftRemain = rs.getInt("eventLeftRemain");
						rightRemain = rs.getInt("eventRightRemain");
						arenaSold = rs.getInt("eventArenaSold");
						leftSold = rs.getInt("eventLeftSold");
						rightSold = rs.getInt("eventRightSold");
					}
					arenaRemain -= Integer.parseInt(arenaOrder.getText());
					arenaSold += Integer.parseInt(arenaOrder.getText());
					leftRemain -= Integer.parseInt(leftOrder.getText());
					leftSold += Integer.parseInt(leftOrder.getText());
					rightRemain -= Integer.parseInt(rightOrder.getText());
					rightSold += Integer.parseInt(rightOrder.getText());

					stmt.executeUpdate("UPDATE eventspecifics SET eventArenaRemain="
							+ arenaRemain
							+ ""
							+ ",eventArenaSold="
							+ arenaSold
							+ ",eventLeftRemain="
							+ leftRemain
							+ ",eventLeftSold="
							+ leftSold
							+ ",eventRightRemain="
							+ rightRemain
							+ ",eventRightSold="
							+ rightSold
							+ " WHERE eventArtist='" + event + "'");

					ResultSet rs1 = stmt
							.executeQuery("SELECT TicketSales FROM accounts WHERE User='"
									+ LoginFrame.getUser() + "'");
					int ticketSales = 0;
					while (rs1.next()) {
						ticketSales = rs1.getInt("TicketSales");
					}
					ticketSales += (Integer.parseInt(arenaOrder.getText())
							+ Integer.parseInt(leftOrder.getText()) + Integer
							.parseInt(rightOrder.getText()));
					stmt.executeUpdate("UPDATE accounts SET TicketSales="
							+ ticketSales + " WHERE User='"
							+ LoginFrame.getUser() + "'");
					JOptionPane.showMessageDialog(null,
							"Tickets booked successfully.");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"Problem booking tickets.");

				}

			}
		} else {
			JOptionPane.showMessageDialog(null, "Please choose a ticket.");
		}
	}
}
