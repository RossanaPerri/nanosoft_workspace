package it.nanosoft.mechAdvisor.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Utente;

public final class QueryMaker implements Loggable {

	public enum QueryOutput {
		UTENTE, OFFICINA
	}

	private PostgreSqlConnection conn = null;

	/**
	 * Eseguee un'istruzione che interroga il database. Chiama il metodo
	 * stampaRisultatoQuery per stampare il risultato
	 * 
	 * @param query       String istruzioni sql
	 * @param queryOutput Enum per stampa output
	 */
	public QueryMaker(String query, QueryOutput queryOutput, int queryNum) {
		try {
			ResultSet rs = null;
			Statement stmt = null;
			conn = PostgreSqlConnection.getInstance();
			stmt = conn.getConnection().createStatement();
			try {
				rs = stmt.executeQuery(query);
			} catch (NullPointerException ex) {
				newloggerApp.error(ex.getMessage());
			}
			if (rs != null) {
				stampaRisultatoQuery(rs, queryOutput, queryNum);
				newloggerApp.info("Query eseguita !");
			}
		} catch (SQLException e) {
			newloggerApp.error("----Errore ! Query non eseguita: " + e.getMessage() + " ----");
		} finally {
			conn.closeConnection();
		}
	}

	/**
	 * Stampa i ridultati di una query
	 * 
	 * @param rs          risultato interrogazione del database
	 * @param queryOutput Enum per stampa output
	 */
	private void stampaRisultatoQuery(ResultSet rs, QueryOutput queryOutput, int queryNum) {
		switch (queryOutput) {
		case UTENTE:
			Map<Integer, Object[]> userTemplate = new TreeMap<Integer, Object[]>();
			try {
				userTemplate.put(1, new Object[] { "NOME", "COGNOME" });
				int countRow = 1;
				while (rs.next()) {
					countRow++;
					Utente utente = new Utente(rs.getString("nome"), rs.getString("cognome"));
					userTemplate.put(countRow, new Object[] { utente.getNome(), utente.getCognome() });
					newloggerApp.info(utente.toString());
				}
				CreateExcelFile.user(userTemplate, queryNum);
			} catch (SQLException e) {
				newloggerApp.error("" + e.getMessage());
			}
			break;
		case OFFICINA:
			Map<Integer, Object[]> workshopTemplate = new TreeMap<Integer, Object[]>();
			try {
				workshopTemplate.put(1, new Object[] { "NOME" });
				int countRow = 1;
				while (rs.next()) {
					countRow++;
					Officina officina = new Officina(rs.getString("nome"));
					workshopTemplate.put(countRow, new Object[] { officina.getNome() });
					newloggerApp.info(officina.toString());
				}
				CreateExcelFile.workshop(workshopTemplate, queryNum);
			} catch (SQLException e) {
				newloggerApp.error(e.getMessage());
			}
			break;
		default:
			newloggerApp.info("Query non trovata!");
			;
		}
		System.out.println();
	}

	public void closeMaker() {
		newloggerApp.info("---- Query Maker closed ----");
	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}
