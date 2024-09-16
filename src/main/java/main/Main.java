package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jdbc.Connector;

public class Main {
	
	static Connection conn;

	public static void main(String[] args) throws SQLException, IOException {
		
		conn = Connector.getInstance().initConn();
	
		conn.close();
	}
}
