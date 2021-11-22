package it.nanosoft.mechAdvisor.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;

/**
 * Questa è la classe che gestisce l'esecuzione delle query.
 * @author emicovi
 *
 */

public class QueryMaker implements Loggable {

	private PostgreSqlConnection conn = null;
	
	private Excel excel = new Excel();
	
	
	public QueryMaker(String query, String nq) {
		try {
			ResultSet rs = null;
			conn = PostgreSqlConnection.getInstance();
			Statement stmt = conn.getConnection().createStatement();
			try { rs = stmt.executeQuery(query);	
			} catch (Exception e) {
				newloggerApp.error(e.getMessage());
			}
			if(rs!=null)
			 excel.toExcel(rs, nq);
		} catch (SQLException e) {
			newloggerApp.error("Errore !" + e.getMessage());
		} catch (FileNotFoundException e) {
			newloggerApp.error(e.getMessage());
		} catch (IOException e) {
			newloggerApp.error(e.getMessage());
		} finally {
			PostgreSqlConnection.closeConnection();
		}
	}
	
	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
	
}