package it.nanosoft.mechAdvisor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSqlConnection {
	private static PostgreSqlConnection instance;
	private Connection connection;
	String host = null;
	String username = null;
	String password = null;
	String driver = null;

	private PostgreSqlConnection() throws SQLException {
		try {
			Properties prop = new Properties();
			prop.load(PostgreSqlConnection.class.getClassLoader().getResourceAsStream("connection.properties"));

			host = prop.getProperty("host").toString();
			username = prop.getProperty("username").toString();
			password = prop.getProperty("password").toString();
			driver = prop.getProperty("driver").toString();

			Class.forName(driver);
			this.connection = DriverManager.getConnection(host, username, password);
			System.out.println("Connessione Riuscita!");
		} catch (Exception ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PostgreSqlConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new PostgreSqlConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new PostgreSqlConnection();
		}
		return instance;
	}
}