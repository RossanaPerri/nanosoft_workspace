package it.nanosoft.mechAdvisor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSqlConnection {
	
	private String url = "jdbc:postgresql://35.180.170.147:5432/postgres";
	private String user = "postgres";
	private String password = "!2020!pg";
	
	
	private void connect() {
		try(Connection connection = DriverManager.getConnection(url, user, password)){
			if(connection!=null) {
				System.out.println("Connected to PostgreSql!");
			}else {
				System.out.println("Failed to connect to the DB");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
	public static void main(String[] args) {
		PostgreSqlConnection sqlConnection = new PostgreSqlConnection();
		
		sqlConnection.connect();
	}
}
