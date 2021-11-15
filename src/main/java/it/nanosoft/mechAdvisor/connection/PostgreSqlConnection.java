package it.nanosoft.mechAdvisor.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
			    File targetFile = new File("/Users/emicovi/Documents/workspace/nanosoft_MechAdvisor/src/main/resources/mydb.cfg");
			    try {
					prop.load(new java.io.FileInputStream(targetFile));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				host = prop.getProperty("host").toString();
				username = prop.getProperty("username").toString();
				password = prop.getProperty("password").toString();
				driver = prop.getProperty("driver").toString();

	            Class.forName("org.postgresql.Driver");
	            this.connection = DriverManager.getConnection(host, username, password);
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
	        }
	    }

	    public Connection getConnection() {
	        return connection;
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
