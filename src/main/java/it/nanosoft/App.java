package it.nanosoft;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Recensione;
import it.nanosoft.mechAdvisor.model.Utente;
import it.nanosoft.mechAdvisor.service.EmailSender;
import it.nanosoft.mechAdvisor.service.Loggable;
import it.nanosoft.mechAdvisor.service.QueryMaker;
import it.nanosoft.mechAdvisor.service.ReportMaker;
import it.nanosoft.mechAdvisor.util.Costanti;

/**
 * Questa è la classe principale. Avvia il programma.
 * 
 * @author RossanaPerri
 *
 */
public class App implements Loggable, Costanti {
	/**
	 * carica le istruzioni sql dal file "query.list"
	 * 
	 * @return un oggetto Properties che contiente l'insieme delle chiavi il cui
	 *         valore corrisponde all'istruzione sql da eseguire
	 */
	private static Properties leggiQuery() {
		Properties prop = new Properties();
		try {
			newloggerApp.info("Sto caricando il file " + "\"FILE_QUERY_LIST\"");
			prop.load(App.class.getClassLoader().getResourceAsStream(FILE_QUERY_LIST));
		} catch (IOException e) {
			newloggerApp.error(" ---- Impossibile caricare il file " + "\"FILE_QUERY_LIST\": ", e);
		}
		return prop;
	}

	public static void main(String[] args) {
		
		QueryMaker qm;
		ReportMaker rm = new ReportMaker();
		Properties prop = leggiQuery();
		EmailSender emailSender = new EmailSender();
		String query = null;

		newloggerApp.info(
				"1) Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto primaria era un AUDI");
		try {
			query = prop.getProperty("query1").toString();
			qm = new QueryMaker();
			List<Utente> userList = qm.makeQueryUtente(query);
			rm.createUtenteReports(userList, REPORT_UTENTE_EXCEL);
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_UTENTE, REPORT_UTENTE_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(" ----- : ", e);
		}

		newloggerApp.info("2) Le migliori 6 officine, calcolando il voto medio di ogni officina");
		try {
			query = prop.getProperty("query8").toString();
			qm = new QueryMaker();
			List<Officina> officinaList = qm.makeQueryOfficina(query);
			rm.createOfficinaReports(officinaList, REPORT_OFFICINA_EXCEL);	
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_OFFICINA, REPORT_OFFICINA_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}

		newloggerApp
				.info("3) Tutti gli utenti della provincia di CZ che hanno effettuato almeno una sostituzione gomme");
		try {
			query = prop.getProperty("query3").toString();
			qm = new QueryMaker();
			List<Utente> userList = qm.makeQueryUtente(query);
			rm.createUtenteReports(userList, REPORT_UTENTE_EXCEL);
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_UTENTE, REPORT_UTENTE_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}

		newloggerApp.info("4) Tutti i meccanici che possiedono le officine migliori");
		try {
			query = prop.getProperty("query4").toString();
			qm = new QueryMaker();
			List<Utente> userList = qm.makeQueryUtente(query);;
			rm.createUtenteReports(userList, REPORT_UTENTE_EXCEL);
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_UTENTE, REPORT_UTENTE_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}

		newloggerApp.info("5) Gli utenti di tipo admin che sono anche utenti dell'applicazione che hanno lasciato "
				+ "almeno una recensione positiva ( il cui valore è >=6 )");
		try {
			query = prop.getProperty("query5").toString();
			qm = new QueryMaker();
			List<Utente> userList = qm.makeQueryUtente(query);
			rm.createUtenteReports(userList, REPORT_UTENTE_EXCEL);
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_UTENTE, REPORT_UTENTE_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}

		newloggerApp.info("6) I 2 utenti che hanno lasciato più recensioni negative");
		try {
			query = prop.getProperty("query6").toString();
			qm = new QueryMaker();
			List<Utente> userList = qm.makeQueryUtente(query);
			rm.createUtenteReports(userList, REPORT_UTENTE_EXCEL);
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_UTENTE, REPORT_UTENTE_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}

		newloggerApp.info("7) Tutti gli utenti che in possesso di un motorino che hanno lasciato almeno "
				+ "una recensione negativa nella provincia di CS");
		try {
			query = prop.getProperty("query7").toString();
			qm = new QueryMaker();
			List<Utente> userList = qm.makeQueryUtente(query);
			rm.createUtenteReports(userList, REPORT_UTENTE_EXCEL);
			emailSender.inviaEmail("roxana.perri18@gmail.com", OGGETTO_MAIL_REPORT_UTENTE, REPORT_UTENTE_EXCEL);
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
	}

	@Override
	public Logger logging() {
		return null;
	}
}
