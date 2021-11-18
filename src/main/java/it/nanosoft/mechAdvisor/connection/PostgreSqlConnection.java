package it.nanosoft.mechAdvisor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class PostgreSqlConnection {
	private static PostgreSqlConnection instance;
	private Connection connection;
	String host = null;
	String username = null;
	String password = null;
	String driver = null;
	
	/**
	 * Costruttore privato che crea una connessione con DBpostgres
	 * inizializza le variabili di classe con dati configurazione letti da pathclass 
	 */

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
			System.out.println("---- Successful database connection creation ---- \n");
		} catch (Exception ex) {
			System.out.println("---- Database Connection Creation Failed : " + ex.getMessage() + " ---- \n");
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Chiude la connessione
	 */

	public void closeConnection() {
		try {
			connection.close();
			System.out.println("---- Connection closed ---- \n");
		} catch (SQLException e) {
			System.out.println("---- Connection closing error : " + e.getMessage() + " ---- \n");
		}
	}
	/**
	 * Verifica se la connessione è stata già creata, altrimenti ne crea una nuova  
	 * In questo modo l'istanza viene create al primo tentativo di utilizzo e 
	 * nelle successive chiamate viene restituito il riferimento alla classe istanziata.
	 * @throws SQLException
	 */
	public static PostgreSqlConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new PostgreSqlConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new PostgreSqlConnection();
		}
		return instance;
	}
}