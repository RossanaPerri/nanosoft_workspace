package it.nanosoft.mechAdvisor.connection;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.service.Loggable;

/**
 * Questa è la classe per la connesione al db in modalità singleton.
 * 
 * @author emicovi
 *
 */

public class PostgreSqlConnection implements Loggable {

	private static PostgreSqlConnection instance;
	private static Connection connection = null;
	String host = null;
	String username = null;
	String password = null;
	String driver = null;

	// Creating method to open the connection with database
	private PostgreSqlConnection() {
		// Using try catch structure to treat any SQL Exceptions
		try {
			Properties prop = new Properties();
			// Retrieving auth info
			try {
				// Loading properties
				newloggerApp.info("Carico i dati per la connesione al db dal file connection.properties");
				prop.load(PostgreSqlConnection.class.getClassLoader().getResourceAsStream("connection.properties"));
			} catch (FileNotFoundException e) {
				newloggerApp.error("Non riesco a caricare i dati per la connesione al db dal file connection.properties "+ e.getMessage());
			} catch (Exception e) {
				newloggerApp.error(e.getMessage());
			}

			host = prop.getProperty("host").toString();
			username = prop.getProperty("username").toString();
			password = prop.getProperty("password").toString();
			driver = prop.getProperty("driver").toString();

			Class.forName("org.postgresql.Driver");

			/*
			 * Initializing connection through DriverManager with url and user/password
			 * props passed as getConnection parameters
			 */
			connection = DriverManager.getConnection(host, username, password);
		} catch (ClassNotFoundException ex) {
			newloggerApp.info("Database Connection Creation Failed : " + ex.getMessage());
		} catch (SQLException e) {
			newloggerApp.error(e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static void closeConnection() {
		// Testing whether connection is null in order to close it
		if (connection != null) {
			// Using try catch structure to treat any SQL Exceptions
			try {
				newloggerApp.info("Chiudo la connessione al DB");
				connection.close();
			} catch (SQLException e) {
				newloggerApp.error(e.getMessage());
			}
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

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}

}
