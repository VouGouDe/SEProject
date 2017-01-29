package com.se_project.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.se_project.connection.ConnectionToMySQL;

public class GraphMaker extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3123031565869273733L;
	private Connection myConnection = ConnectionToMySQL.getConnection();
	private JPanel contentPane;
	private JPanel graphPanel;
	private DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBox;

	public GraphMaker() {
		setResizable(false);
		setTitle("Ticket Sales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		

		comboBoxModel.addElement("Account Ticket Sales");
		getEvents();
		comboBox = new JComboBox<String>(comboBoxModel);
		contentPane.add(comboBox, BorderLayout.NORTH);
		
		graphPanel = new JPanel();
		contentPane.add(graphPanel, BorderLayout.CENTER);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				ActionEvent boxSelected = arg0;
				System.out.println(boxSelected);
				graphSelection(boxSelected);
			}});
	}

	public void getEvents() {
		try {
			Statement stmt = myConnection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT eventArtist FROM event");
			while (rs.next()) {
				comboBoxModel.addElement(rs.getString("eventArtist"));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problem loading events.");
		}
	}
	
	public void graphSelection(ActionEvent arg0){
		JComboBox<?> cb = (JComboBox<?>) arg0.getSource();
		String msg = (String) cb.getSelectedItem();
		if (msg != "Account Ticket Sales") {
			int leftS=0;
			int rightS=0;
			int arenaS=0;
			int leftT=0;
			int rightT=0;
			int arenaT=0;					
			try {
				Statement stmt = myConnection.createStatement();
				ResultSet rs = stmt
					.executeQuery("SELECT eventArenaRemain,eventArenaSold,eventLeftRemain,eventLeftSold,eventRightRemain,eventRightSold FROM eventspecifics WHERE eventArtist='"
								+ msg + "'");						
				while (rs.next()) {
					leftS=rs.getInt("eventLeftSold");
					rightS=rs.getInt("eventRightSold");
					arenaS=rs.getInt("eventArenaSold");
					leftT=rs.getInt("eventLeftSold")+rs.getInt("eventLeftRemain");
					rightT=rs.getInt("eventRightSold")+rs.getInt("eventRightRemain");
					arenaT=rs.getInt("eventArenaSold")+rs.getInt("eventArenaRemain");
				}
				DefaultCategoryDataset dataset= new DefaultCategoryDataset();		
				String left= "Left Tickets";
				String arena= "Arena Tickets";
				String right= "Right Tickets";
				
				dataset.addValue(arenaT, "Total", arena);
				dataset.addValue(leftT, "Total", left);
				dataset.addValue(rightT, "Total", right);
				
				dataset.addValue(arenaS, "Sold", arena);
				dataset.addValue(leftS, "Sold", left);
				dataset.addValue(rightS, "Sold", right);
				
				JFreeChart chart = ChartFactory.createBarChart(msg+" Ticket Sales", "Ticket Categories", "# of Tickets", dataset,PlotOrientation.VERTICAL,true,true,false);						
				graphPanel.removeAll();
				ChartPanel chartPanel = new ChartPanel(chart);
				graphPanel.add(chartPanel);
				graphPanel.repaint();
				graphPanel.revalidate();
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"Problem loading chart.");
			}
		}else{
			List<String> users = new ArrayList<String>();
			List<Integer> sales = new ArrayList<Integer>();
			try {
				Statement stmt = myConnection.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT User,TicketSales FROM accounts WHERE Admin=0");
				while (rs.next()) {
					users.add(rs.getString("User"));
					sales.add(rs.getInt("TicketSales"));							
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Problem loading chart.");
			}
			
			
			DefaultCategoryDataset dataset= new DefaultCategoryDataset();			
			for(int i=0;i<users.size();i++){
				dataset.addValue(sales.get(i), "Sales", users.get(i));
			}
			JFreeChart chart = ChartFactory.createBarChart(msg+" Ticket Sales", "Ticket Sales", "# of Tickets", dataset,PlotOrientation.VERTICAL,true,true,false);						
			graphPanel.removeAll();
			ChartPanel chartPanel = new ChartPanel(chart);
			graphPanel.add(chartPanel);
			graphPanel.repaint();
			graphPanel.revalidate();
			
		}
		
	}
}
