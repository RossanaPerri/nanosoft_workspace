package it.nanosoft.mechAdvisor.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;

public class QueryServices {
		
	public static ResultSet executeQuery(String query) throws SQLException{
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
        try{
			conn = PostgreSqlConnection.getInstance().getConnection();
			stmt =  conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
				System.out.println(rs.getString(rs.getRow()));
            	}
        }catch(Exception err){
            System.err.println("Errore :" + err.getMessage());
        }finally {
        	
        	/*Since Connection, PreparedStatement and ResultSet are
  		  external resources (not controlled by Java JVM), it is 
  		  important to close manually each of them in a finally clause*/
			
			//Calling the specific closing methods in order to avoid multiple try catches
			PostgreSqlConnection.closeResultSet(rs);
			
			//Upcast in closeStatement as PreparedStatement pst is passed as a Statement parameter
			PostgreSqlConnection.closeStatement(stmt);
			
			PostgreSqlConnection.closeConnection();
		}
		
        
        return rs;
    }
}
