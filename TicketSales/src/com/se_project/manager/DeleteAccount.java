package com.se_project.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.se_project.connection.ConnectionToMySQL;

public class DeleteAccount extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7252312741957948602L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblDeletelabel;
	private JButton btnNewButton;
	private static Connection myConnection = ConnectionToMySQL.getConnection();

	public DeleteAccount() {
		setResizable(false);
		setTitle("Delete Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDeletelabel = new JLabel("Select Account to Delete",
				SwingConstants.CENTER);
		lblDeletelabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeletelabel.setBounds(119, 11, 192, 29);
		contentPane.add(lblDeletelabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 50, 328, 153);
		contentPane.add(scrollPane);

		table = new JTable(tableModel());
		scrollPane.setViewportView(table);
		table.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));

		btnNewButton = new JButton("Delete Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accountDeletion();
			}
		});
		btnNewButton.setBounds(146, 227, 135, 23);
		contentPane.add(btnNewButton);
	}

	public static DefaultTableModel tableModel() {

		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<Vector<String>> column = new Vector<Vector<String>>();
		Vector<String> vector2 = new Vector<String>();
		vector2.add("User");
		column.add(vector2);
		try {
			Statement stmt = myConnection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT User FROM accounts WHERE User!='Manager'");
			while (rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString("User"));
				data.add(vector);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem loading Accounts.");
		}
		DefaultTableModel myModel = new DefaultTableModel(data, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3333260796090453980L;

			/**
			 * 
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		myModel.fireTableDataChanged();
		return (myModel);

	}
	
	public void accountDeletion(){
		try {
			if (table.getSelectedRowCount() > 0) {
				int selectedRow[] = table.getSelectedRows();
				for (int i : selectedRow) {
					String toDelete = table.getValueAt(i, 0).toString();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane
							.showConfirmDialog(
									null,
									"Are you sure you want to delete the "
											+ toDelete
											+ " account? This decision is final.",
									"Warning", dialogButton);
					if (dialogResult == 0) {
						Statement stmt = myConnection.createStatement();
						stmt.executeUpdate("DELETE FROM accounts WHERE User='"
								+ toDelete + "'");
						JOptionPane.showMessageDialog(null,
								"Entry was deleted.");
						((DefaultTableModel) table.getModel())
								.removeRow(i);
						table.revalidate();
						table.repaint();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Please select an account.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Problem with deleting entry");
		}
	}
}
