package it.nanosoft.mechAdvisor.service;

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

	public enum QueryOutput {UTENTE, OFFICINA}

	private PostgreSqlConnection conn = null;
	
	
	public QueryMaker(String query, QueryOutput queryOutput) {
		try {
			ResultSet rs = null;
			conn = PostgreSqlConnection.getInstance();
			Statement stmt = conn.getConnection().createStatement();
			try { rs = stmt.executeQuery(query);	
			} catch (Exception e) {
				newloggerApp.error(e.getMessage());
			}
			if(rs!=null)
			stampaRisultatoQuery(rs, queryOutput);
		} catch (SQLException e) {
			newloggerApp.error("Errore !" + e.getMessage());
		} finally {
			PostgreSqlConnection.closeConnection();
		}
	}
	private void stampaRisultatoQuery(ResultSet rs, QueryOutput queryOutput) {
		switch (queryOutput) {
		case UTENTE:
			try {
				while (rs.next()) {
					newloggerApp.info(rs.getString("nome") +" "+ rs.getString("cognome"));
				}
			} catch (SQLException e) {
				newloggerApp.error(e.getMessage());
			}
			break;
		case OFFICINA:
			try {
				while (rs.next()) {
					newloggerApp.info(rs.getString("nome"));
				}
			} catch (SQLException e) {
				newloggerApp.error(e.getMessage());
			}
			break;
		default:
			newloggerApp.error("Query non trovata!");;
		}
		System.out.println();
	}
	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
	
}