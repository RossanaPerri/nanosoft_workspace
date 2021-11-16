package it.nanosoft.mechAdvisor.queryService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Utente;

public class QueryMaker {

	public enum QueryOutput {UTENTE, OFFICINA}

	private PostgreSqlConnection conn = null;
	
	/**
	 * 
	 * @param query 
	 * @param queryOutput
	 */
	public QueryMaker(String query, QueryOutput queryOutput) {
		try {
			conn = PostgreSqlConnection.getInstance();
			Statement stmt = conn.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			stampaRisultatoQuery(rs, queryOutput);
		} catch (SQLException e) {
			System.out.println("Errore !");
			e.printStackTrace();
		} finally {
			conn.closeConnection();
		}
	}
	private void stampaRisultatoQuery(ResultSet rs, QueryOutput queryOutput) {
		switch (queryOutput) {
		case UTENTE:
			try {
				while (rs.next()) {
					Utente utente = new  Utente(rs.getString("nome"), rs.getString("cognome"));
					System.out.println(utente);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case OFFICINA:
			try {
				while (rs.next()) {
					Officina officina = new  Officina(rs.getString("nome"));
					System.out.println(officina);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Query non trovata!");;
		}
		System.out.println();
	}
	
}
