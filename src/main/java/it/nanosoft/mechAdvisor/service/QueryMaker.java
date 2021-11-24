package it.nanosoft.mechAdvisor.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Utente;

/**
 * Questa classe gestisce l'esecuzione delle query.
 * 
 * @author RossanaPerri
 *
 */
public final class QueryMaker implements Loggable {

	private PostgreSqlConnection conn;
	private Statement stmt;
	private ResultSet rs;

	/**
	 * Costruttore di Default
	 */
	public QueryMaker() {
	}

	/**
	 * Gestisce le query che restituisco un risultato di tipo Utente
	 * 
	 * @param query istruzione sql
	 * @return lista di utenti inizializzati a partire dal ResultSet
	 */
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
			newloggerApp.error(" ---- : ", e);
		} finally {
			conn.closeConnection();
		}
		return listToReturn;
	}

	/**
	 * Gestisce le query che restituisco un risultato di tipo Officina
	 * 
	 * @param query istruzioni sql
	 * @return lista di officine inizializzate a partire dal ResultSet
	 */
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
		} finally {
			conn.closeConnection();
		}
		return listToReturn;
	}

	@Override
	public Logger logging() {
		return null;
	}
}
