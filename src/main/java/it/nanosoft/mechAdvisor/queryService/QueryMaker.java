package it.nanosoft.mechAdvisor.queryService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Utente;

public final class QueryMaker {

	public enum QueryOutput {UTENTE, OFFICINA}

	private PostgreSqlConnection conn = null;
	
	/**
	 * Eseguee un'istruzione che interroga il database.
	 * Chiama il metodo stampaRisultatoQuery per stampare il risultato
	 * @param query 
	 * 			String istruzioni sql
	 * @param queryOutput 
	 * 			Enum per stampa output
	 */
	public QueryMaker(String query, QueryOutput queryOutput) {
		try {
			conn = PostgreSqlConnection.getInstance();
			Statement stmt = conn.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Query eseguita : ");
			stampaRisultatoQuery(rs, queryOutput);
		} catch (SQLException e) {
			System.out.println("Errore ! Query non eseguita.");
			e.printStackTrace();
		} finally {
			conn.closeConnection();
		}
	}
	
	/**
	 * Stampa i ridultati di una query
	 * @param rs
	 * 		risultato interrogazione del database
	 * @param queryOutput
	 * 		Enum per stampa output
	 */
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
	
	public void closeMaker() {
		System.out.println("\n---- Query Maker closed ----");
	}
	
}
