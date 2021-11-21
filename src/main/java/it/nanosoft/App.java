package it.nanosoft;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.service.Loggable;
import it.nanosoft.mechAdvisor.service.QueryMaker;
import it.nanosoft.mechAdvisor.service.QueryMaker.QueryOutput;

/**
 * 
 * @author Rossana Perri
 *
 */
public class App implements Loggable {

	private static Properties leggiQuery() {
		Properties prop = new Properties();
		try {
			newloggerApp.info("Sto caricando il file query.list");
			prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
		} catch (IOException e) {
			newloggerApp.error("Impossibile caricare il file query.list: " + e.getMessage());
		}
		return prop;
	}

	public static void main(String[] args) {

		QueryMaker qm;
		QueryOutput queryOutput;
		String query = null;
		Properties prop = leggiQuery();

		newloggerApp.info(
				"1) Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto primaria era un AUDI");
		try {
			query = prop.getProperty("query1").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query, queryOutput, 1);

		newloggerApp.info("2) Le migliori 10 officine, calcolando il voto medio di ogni officina");
		try {
			query = prop.getProperty("query2").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.OFFICINA;
		qm = new QueryMaker(query, queryOutput, 2);

		newloggerApp.info("3) Tutti gli utenti della provincia di CZ che hanno effettuato almeno una sostituzione");
		try {
			query = prop.getProperty("query3").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query, queryOutput, 3);

		newloggerApp.info("4) Tutti i meccanici che possiedono le officine migliori");
		try {
			query = prop.getProperty("query4").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query, queryOutput, 4);

		newloggerApp.info("5) Gli utenti di tipo admin che sono anche utenti dell'applicazione che hanno lasciato "
				+ "almeno una recensione positiva ( il cui valore è >=6 )");
		try {
			query = prop.getProperty("query5").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query, queryOutput, 5);

		newloggerApp.info("6) I 2 utenti che hanno lasciato più recensioni negative");
		try {
			query = prop.getProperty("query6").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query, queryOutput, 6);

		newloggerApp.info("7) Tutti gli utenti che in possesso di un motorino che hanno lasciato almeno "
				+ "una recensione negativa nella provincia di CS");
		try {
			query = prop.getProperty("query7").toString();
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query, queryOutput, 7);

		qm.closeMaker();
	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}
