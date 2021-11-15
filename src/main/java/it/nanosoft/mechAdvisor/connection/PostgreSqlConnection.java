package it.nanosoft.mechAdvisor.connection;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSqlConnection {

	public String date = new SimpleDateFormat("dd-MM-yyyy-HH-mm").format(new Date());
    private final String createTable = "CREATE TABLE \"" + date + "\" (id INT, data VARCHAR(100));";
	private static final int LoginTimeout = 10;

	public Connection createConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties prop = new Properties();
		System.out.println("\n\n=======================\nJDBC Connector Test " + date);
		System.out.println("User home directory: " + System.getProperty("user.home"));
		String host;
		String username;
		String password;
		String driver;
		try {
		    File targetFile = new File("/Users/emicovi/Documents/workspace/nanosoft_MechAdvisor/src/main/resources/mydb.cfg");
			prop.load(new java.io.FileInputStream(targetFile));

			host = prop.getProperty("host").toString();
			username = prop.getProperty("username").toString();
			password = prop.getProperty("password").toString();
			driver = prop.getProperty("driver").toString();
		} catch (IOException e) {
			System.out.println("Unable to find mydb.cfg"
					+ "\n Please make sure that configuration file created in this folder.");

			host = "Unknown HOST";
			username = "Unknown USER";
			password = "Unknown PASSWORD";
			driver = "Unknown DRIVER";
		}

		System.out.println(
				"host: " + host + "\nusername: " + username + "\npassword: " + password + "\ndriver: " + driver);

		Class.forName(driver);
		System.out.println("--------------------------");
		System.out.println("DRIVER: " + driver);
		System.out.println("Set Login Timeout: " + LoginTimeout);
		DriverManager.setLoginTimeout(LoginTimeout);
		Connection connection = DriverManager.getConnection(host, username, password);
		System.out.println("CONNECTION: " + connection);

		return connection;
	}
	
//	public String runSqlStatement() {
//        String result = "";
//        try {
//            Statement statement = createConnection().createStatement();
//            System.out.println("SQL query: " + createTable);
//            statement.execute(createTable);
//        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Exception occurred: " + ex);
//            result = ex.getMessage();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            result = ex.getMessage();
//        }
//        return result;
//    }
//}
//
}
