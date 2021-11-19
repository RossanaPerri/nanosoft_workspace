package it.nanosoft;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.menu.GenericMenu;
import it.nanosoft.mechAdvisor.service.Loggable;
import it.nanosoft.mechAdvisor.service.QueryMaker;
import it.nanosoft.mechAdvisor.service.QueryMaker.QueryOutput;

/**
 * Questa è la classe principale che serve per avviare il programma.
 *
 * @author emicovi
 *
 */

public class App implements Loggable {
	public static void main(String[] args) {

		GenericMenu genericMenu = new GenericMenu();

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("1", "Esequi la prima query", new Runnable() {
			public void run() {
				String query = null;

				newloggerApp.info("Eseguo la prima query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query1").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
			}
		});

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("2", "Esequi la seconda query", new Runnable() {
			public void run() {

				String query = null;
				newloggerApp.info("Eseguo la seconda query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query2").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.OFFICINA;
				new QueryMaker(query, queryOutput);
			}
		});

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("3", "Esequi la terza query", new Runnable() {
			public void run() {

				String query = null;
				newloggerApp.info("Eseguo la terza query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query3").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
			}
		});
		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("4", "Esequi la quarta query", new Runnable() {
			public void run() {

				String query = null;
				newloggerApp.info("Eseguo la quarta query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query4").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
			}
		});

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("5", "Esequi la quinta query", new Runnable() {
			public void run() {

				String query = null;
				newloggerApp.info("Eseguo la quinta query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query5").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
			}
		});
		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("6", "Esequi la sesta query", new Runnable() {
			public void run() {
				String query = null;
				newloggerApp.info("Eseguo la sesta query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query6").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
			}
		});

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("7", "Esequi la settima query", new Runnable() {
			public void run() {
				String query = null;
				newloggerApp.info("Eseguo la settima query");
				newloggerApp.info("------Risultato-------");

				QueryOutput queryOutput;

				Properties prop = new Properties();
				try {
					newloggerApp.info("Carico il file delle query");
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
				}
				try {
					query = prop.getProperty("query7").toString();

				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				}
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
			}
		});
		// Finally initalize the menu
		genericMenu.initMenu();

	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}
