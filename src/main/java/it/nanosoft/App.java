package it.nanosoft;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.standard.DateTimeAtCompleted;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		App app = new App();

		// System.out.println(app.getUtenteCount());

		// 1) Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto
		// primaria era un AUDI
		app.query1();
//		// 2) Le migliori 10 officine, calcolando il voto medio di ogni officina
//		app.query2();
//		// 3)Tutti gli utenti della provincia di CZ che hanno effettuato almeno una
//		// sostituzione
//		app.query3();
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

		String SQL = "\r\n" + "	select A.nome, A.cognome, U.email \r\n"
				+ "	from utente as U, anagrafica as A, propieta_veicolo as PV, veicolo as V	\r\n"
				+ "	where A.email_utente = U.email and PV.email_utente = U.email and PV.id_veicolo = V.id \r\n"
				+ "		and PV.tipologia_veicolo = V.tipologia and PV.data_fine_prop <= '2019-11-11' and V.marca ='audi';";

		Connection databaseConnection = null;
		try {
			databaseConnection = PostgreSqlConnection.getInstance().getConnection();
			Statement stmt = databaseConnection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				System.out.println(rs.getString("nome") + " " + rs.getString("cognome"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				databaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
