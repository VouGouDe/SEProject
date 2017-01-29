package com.se_project.connection;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectionToMySQLTest {


	boolean ans=true;
	
	@SuppressWarnings("static-access")
	@Test
	public void testConnectionToMySQL() {
		ConnectionToMySQL con= new ConnectionToMySQL("Ntinos", "Vourloumis");
		assertEquals(ans,con.SuccessfullLogin());
		assertEquals(0,con.getStatus());
		
	}
}
