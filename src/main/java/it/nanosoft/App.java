package it.nanosoft;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.model.Utente;
import it.nanosoft.mechAdvisor.model.Officina;

/**
 * 
 * @author Utente
 *
 */
public class App {
	public static void main(String[] args)  {

		App app = new App();

		// 1) Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto
		// primaria era un AUDI
		app.query1();
		//		// 2) Le migliori 10 officine, calcolando il voto medio di ogni officina
		//		app.query2();
		//		// 3)Tutti gli utenti della provincia di CZ che hanno effettuato almeno una
		//		// sostituzione
		app.query3();
		//		// 4) Tutti i meccanici che possiedono le officine migliori
		//		app.query4();
		//		// 5)Gli utenti di tipo admin che sono anche utenti dell'applicazione che hanno
		//		// lasciato almeno una recensione positiva ( il cui valore è >=6 )
		//		app.query5();
		//		// 6) I 20 utenti che hanno lasciato più recensioni negative
		//		app.query6();
		//		// 8) Tutti gli utenti che in possesso di un motorino che hanno lasciato almeno
		//		// una recensione negativa nella provincia di CS
		//		app.query8();

	}

	/**
	 * Questo metodo restituisce tutti gli utenti che hanno cambiato auto negli
	 * ultimi 2 anni la cui auto primaria era un AUDI
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void query1() {
		String SQL = "select A.nome, A.cognome, U.email "
				+ "from mech.utente as U, mech.anagrafica as A, mech.propieta_veicolo as PV, mech.veicolo as V "
				+ "where A.email_utente = U.email and PV.email_utente = U.email and PV.id_veicolo = V.id "
				+ "and PV.tipologia_veicolo = V.tipologia and PV.data_fine_prop <= '2019-11-11' and V.marca ='audi'";
		PostgreSqlConnection conn = null;
		try {
			conn = new PostgreSqlConnection();
			Statement stmt = conn.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				//System.out.println(rs.getString("nome") + " " + rs.getString("cognome"));
				Utente utente = new  Utente(rs.getString("nome"), rs.getString("cognome"));
				System.out.println(utente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void query3() {
		String SQL = "\r\n"
				+ "	SELECT o.nome	FROM mech.officina o \r\n"
				+ "	WHERE votomedio notnull\r\n"
				+ "	ORDER BY votomedio desc\r\n"
				+ "	limit 10";
		PostgreSqlConnection conn = null;
		try {
			conn = new PostgreSqlConnection();
			Statement stmt = conn.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				Officina officina = new  Officina (rs.getString("nome"));
				System.out.println(officina);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}