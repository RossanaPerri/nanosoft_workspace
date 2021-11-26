package it.nanosoft.mechAdvisor.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Recensione;
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
		Map<String, Officina> mapOfficine = new TreeMap<String, Officina>();
		try {
			conn = PostgreSqlConnection.getInstance();
			stmt = conn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Recensione recensione = new Recensione(rs);
				Officina officinaToAdd = new Officina(rs);
				// se l'officina non è contenuto nella mappa
				// aggiungo la recensione alla lista delle recensioni dell'Officina
				// aggiungo l'officina nella mappa
				if (!(mapOfficine.containsKey(officinaToAdd.getNome()))) { 
					officinaToAdd.aggiungiRecensione(recensione);
					mapOfficine.put(officinaToAdd.getNome(), officinaToAdd);
				} else {
//					// se l'officina è contenuto nella mappa
					// aggiungo la recensione alla lista delle recensioni dell'Officina
					mapOfficine.get(officinaToAdd.getNome()).aggiungiRecensione(recensione);
				}
			}
		} catch (SQLException e) {
			newloggerApp.error(" ---- : ", e);
		} finally {
			conn.closeConnection();
		}
		for (String key : mapOfficine.keySet()) {
			listToReturn.add(mapOfficine.get(key));
		}
		return listToReturn;
	}

	@Override
	public Logger logging() {
		return null;
	}
}
