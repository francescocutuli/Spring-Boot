package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class Connector {
	
	private static Connection conn = null;
	private static Connector instance;
	
	private Connector() {};
	
	public static Connector getInstance() {
		if(Connector.instance == null) Connector.instance = new Connector();
		return Connector.instance;
	}
	
	public static Connection initConn() throws SQLException{
		if(conn == null || conn.isClosed()) {
			try{
				ResourceBundle resources = ResourceBundle.getBundle("application");
				
				String connectionString = resources.getString("connectionString");
				String host = resources.getString("host");
				String port = resources.getString("port");
				String database = resources.getString("database");
				String options = resources.getString("options");
				connectionString += host + ":" + port + "/" + database + "?" + options;
				
				String user = resources.getString("user");
				String password = resources.getString("password");
				
				Properties props = new Properties();
				props.setProperty("user", user);
				props.setProperty("password", password);
					
				conn = DriverManager.getConnection(connectionString, props);
			 }catch(SQLException e){
				System.err.println("Connessione non riuscita.");
				System.out.println(e.getMessage());
				e.printStackTrace();
			 }
		}
		return conn;
	}
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}