package com.se_project.connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionToMySQL {
	private static java.sql.Connection conn = null;
	private java.sql.Statement stmt = null;
	private ResultSet rs = null;
	private boolean foundIt = false;
	private boolean dbProblem = false;
	private static int adminStatus = 0;
	private static String connectionUrl = "jdbc:mysql://localhost:3306/eventdb?autoReconnect=true&useSSL=false";
	private static String connectionUser = "user";
	private static String connectionPassword = "password";

	public ConnectionToMySQL(String user, String pass) {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			foundIt = false;
			int count = 0;
			conn = DriverManager.getConnection(connectionUrl, connectionUser,
					connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM accounts WHERE User='" + user
					+ "' AND Password='" + pass + "'");
			while (rs.next()) {
				foundIt = true;
				count++;
				adminStatus = rs.getInt("Admin");
			}
			if (count > 1) {
				JOptionPane.showMessageDialog(null, "Nice try.");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Problem with Database spotted.");
			dbProblem = true;
		}
	}

	public boolean SuccessfullLogin() {
		boolean noError = false;
		if (dbProblem) {
			JOptionPane.showMessageDialog(null, "Please check database");
		}
		if (foundIt == false) {
			JOptionPane.showMessageDialog(null, "Wrong Username or Password");
		} else {
			JOptionPane.showMessageDialog(null, "Login Successful!");
			noError = true;
		}
		return noError;
	}

	public static int getStatus() {
		return adminStatus;
	}

	public static java.sql.Connection getConnection() {
		try {
			conn = DriverManager.getConnection(connectionUrl, connectionUser,
					connectionPassword);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Problem with Database spotted.");
			conn = null;
		}
		return conn;
	}

}
