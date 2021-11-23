package it.nanosoft.mechAdvisor.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.*;

/**
 * Questa è la classe che gestisce l'esecuzione delle query.
 * @author emicovi
 *
 */

public class QueryMaker implements Loggable {

	private PostgreSqlConnection conn;
	private Statement stmt;
	private ResultSet rs;

	/**
	 * 
	 * @param query       String istruzioni sql
	 */
	public QueryMaker() {
		
	}
	
	public List<Utente> makeQueryUtente(String query) {
		List<Utente> listToReturn = new ArrayList<Utente>();
		try {
			conn = PostgreSqlConnection.getInstance();
			stmt = conn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Utente userToAdd = new Utente(rs);
				listToReturn.add(userToAdd);
			}
		} catch (SQLException e) {
			newloggerApp.error(e.getMessage());
		}
		return listToReturn;
	}

	public void closeMaker() {
		newloggerApp.info("---- Query Maker closed ----");
	}
	
	public List<Officina> makeQueryOfficina(String query) {
		List<Officina> listToReturn = new ArrayList<Officina>();
		try {
			conn = PostgreSqlConnection.getInstance();
			stmt = conn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Officina officinaToAdd = new Officina(rs);
				listToReturn.add(officinaToAdd);
			}
		} catch (SQLException e) {
			newloggerApp.error(" ---- : ", e);
		}
		return listToReturn;
	}
	

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}