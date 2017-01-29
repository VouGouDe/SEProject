package com.se_project.manager;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.se_project.connection.ConnectionToMySQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteEvent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2503247640669927693L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblDeletelabel;
	private JButton btnNewButton;
	private Connection myConnection = ConnectionToMySQL.getConnection();

	public DeleteEvent() {
		setResizable(false);
		setTitle("Delete Event");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDeletelabel = new JLabel("Select Event to Delete",
				SwingConstants.CENTER);
		lblDeletelabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeletelabel.setBounds(123, 11, 180, 29);
		contentPane.add(lblDeletelabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 50, 328, 153);
		contentPane.add(scrollPane);

		table = new JTable(tableModel());
		scrollPane.setViewportView(table);
		table.setBorder((BorderFactory.createLineBorder(new Color(0), 1)));

		btnNewButton = new JButton("Delete Event");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventDeletion();
			}
		});
		btnNewButton.setBounds(146, 227, 135, 23);
		contentPane.add(btnNewButton);
	}

	public DefaultTableModel tableModel() {

		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<Vector<String>> column = new Vector<Vector<String>>();
		Vector<String> vector2 = new Vector<String>();
		vector2.add("Artist");
		column.add(vector2);
		try {
			Statement stmt = myConnection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT eventArtist FROM eventbasics");
			while (rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString("eventArtist"));
				data.add(vector);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem loading Artists.");
		}
		DefaultTableModel myModel = new DefaultTableModel(data, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5169131341517769239L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		myModel.fireTableDataChanged();
		return (myModel);

	}
	
	public void eventDeletion(){
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
											+ " event? This decision is final.",
									"Warning", dialogButton);
					if (dialogResult == 0) {
						Statement stmt = myConnection.createStatement();
						stmt.executeUpdate("DELETE FROM event WHERE eventArtist='"
								+ toDelete + "'");
						JOptionPane.showMessageDialog(null,
								"Entry was deleted."+ "Please log back in to see updated events.");
						((DefaultTableModel) table.getModel())
								.removeRow(i);
						table.revalidate();
						table.repaint();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Please select an artist.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Problem with deleting entry");
		}
	}
}