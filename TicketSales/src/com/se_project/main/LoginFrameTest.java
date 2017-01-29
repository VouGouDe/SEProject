package com.se_project.main;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.se_project.connection.ConnectionToMySQL;
import com.se_project.manager.CreateAccount;
import com.se_project.manager.DeleteAccount;

public class LoginFrameTest {
	private static LoginFrame frame;

	@Test
	public void testMain() {
		frame = new LoginFrame();
	}

	@SuppressWarnings("static-access")
	@Test
	public void testStartApp(){
		String userName = "Ntinos";
		String pass = "Vourloumis";
		boolean ans=true;
		ConnectionToMySQL newConnection = new ConnectionToMySQL(userName, pass);
		assertEquals(ans,newConnection.SuccessfullLogin());
		assertEquals(0,newConnection.getStatus());
		if (newConnection.SuccessfullLogin()) {	
			frame.dispose();
			MainPage MainFrame = new MainPage();
			MainFrame.setVisible(true);
			MainFrame.checkText();
			TicketSelection tick = new TicketSelection();
			tick.initBoxes("Bon Jovi");
			tick.ticketTransaction();
			CreateAccount acc=new CreateAccount();
			acc.accountCreation("Takis", "123");
			DeleteAccount accDel=new DeleteAccount();
			accDel.accountDeletion();
			MainFrame.logout();
		}
	}
}
