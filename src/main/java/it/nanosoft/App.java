package it.nanosoft;

import java.io.IOException;
import java.sql.SQLException;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;

public class App {
	public static void main(String[] args) {
			PostgreSqlConnection sqlConnection = new PostgreSqlConnection();
			
			try {
				sqlConnection.createConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
