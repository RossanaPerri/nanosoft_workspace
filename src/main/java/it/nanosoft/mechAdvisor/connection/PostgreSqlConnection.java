package it.nanosoft.mechAdvisor.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

	public class PostgreSqlConnection {

	    private static PostgreSqlConnection instance;
	    private static Connection connection = null;
	    String host = null;
		String username = null;
		String password = null;
		String driver = null;
		
   
		//Creating method to open the connection with database
	    private PostgreSqlConnection() {
			//Using try catch structure to treat any SQL Exceptions	
	        try {
	    		Properties prop = new Properties();
	    		//Retrieving auth info
			    try {
					//Loading properties
			    	prop.load(PostgreSqlConnection.class.getClassLoader().getResourceAsStream("connection.properties"));
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
	            
	            /*Initializing connection through DriverManager
				  with url and user/password props passed as 
				  getConnection parameters
				 */
	            connection = DriverManager.getConnection(host, username, password);
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
	        }
	        catch(SQLException e) {
				/*Throwing custom DBException (which extends RuntimeException
				  in order to avoid compilation interruptions (excessive try catches)*/ 
				throw new DBException(e.getMessage());
			}
	    }

	    public Connection getConnection() {
	        return connection;
	    }
	    
	    public static void closeConnection() {
			//Testing whether connection is null in order to close it
			if(connection != null) {
				//Using try catch structure to treat any SQL Exceptions	
				try {
					connection.close();
				}
				catch (SQLException e) {
				/*Throwing custom DBException (which extends RuntimeException
			  	  in order to avoid compilation interruptions (excessive try catches)*/ 				
				throw new DBException(e.getMessage());	
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
	    
	    
	    
	    
/*		=============== 
        CLOSING METHODS
        ===============
*/

/*Specific methods for closing Statement and ResultSet resources in order to
* avoid multiple try catches at MainProgram through reuse and delegation,
* once the specific methods will treat the exceptions with a try catch 
* structure and throw a DBException in case anything occurs, which comes in
* handy since DBException extends RuntimeException, and will not disturb the
* code with multiple compilation requests
*/
public static void closeStatement(Statement pst) {
	if (pst != null) {
		try {
			pst.close();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
}

public static void closeResultSet(ResultSet rs) {
	if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
}

public static void closeConnection(Connection con) {
	if (con != null) {
		try {
			con.close();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
}

}
