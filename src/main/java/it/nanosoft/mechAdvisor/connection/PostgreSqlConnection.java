package it.nanosoft.mechAdvisor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.service.Loggable;

/**
 * Questa è la classe per la connesione al db in modalità singleton.
 * 
 * @author RossanaPerri
 *
 */
public final class PostgreSqlConnection implements Loggable {

	private static PostgreSqlConnection instance;
	private Connection connection;
	String host = null;
	String username = null;
	String password = null;
	String driver = null;

	/**
	 * Costruttore privato che crea una connessione con DBpostgres inizializza le
	 * variabili di classe con dati configurazione letti da pathclass
	 */
	private PostgreSqlConnection() {
		try {
			Properties prop = new Properties();
			prop.load(PostgreSqlConnection.class.getClassLoader().getResourceAsStream("connection.properties"));

			host = prop.getProperty("host").toString();
			username = prop.getProperty("username").toString();
			password = prop.getProperty("password").toString();
			driver = prop.getProperty("driver").toString();

			Class.forName(driver);
			this.connection = DriverManager.getConnection(host, username, password);
			newloggerApp.info("---- Successful database connection creation ---- \n");
		} catch (Exception ex) {
			newloggerApp.error("---- Database Connection Creation Failed : ", ex);
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
			newloggerApp.info("---- Connection closed ---- \n");
		} catch (Exception e) {
			newloggerApp.error("---- Connection closing error : ", e);
		}
	}

	/**
	 * Verifica se la connessione è stata già creata o chiusa, altrimenti ne crea
	 * una nuova
	 * 
	 * @return una una nuova istanza della classe al primo tentativo di utilizzo e
	 *         nelle successive chiamate viene restituito il riferimento alla classe
	 *         istanziata.
	 */
	public static PostgreSqlConnection getInstance() {
		if (instance == null) {
			instance = new PostgreSqlConnection();
		} else
			try {
				if (instance.getConnection().isClosed()) {
					instance = new PostgreSqlConnection();
				}
			} catch (SQLException e) {
				newloggerApp.error("---- Database Connection Creation Failed : ", e);
			}
		return instance;
	}

	@Override
	public Logger logging() {
		return null;
	}
}