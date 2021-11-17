package it.nanosoft;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import it.nanosoft.mechAdvisor.menu.GenericMenu;
import it.nanosoft.mechAdvisor.services.QueryMaker;
import it.nanosoft.mechAdvisor.services.QueryMaker.QueryOutput;

/**
 * Questa è la classe principale che serve per avviare il programma.
 *
 * @author emicovi
 *
 */

public class App {
	public static void main(String[] args) {
		
		
		GenericMenu genericMenu = new GenericMenu();

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("1", "Esequi la prima query", new Runnable() {
			@Override
			public void run() {
				System.out.println("Eseguo la prima query");
				System.out.println("------Risultato-------");
				
				QueryOutput queryOutput;
				
				Properties prop = new Properties();
				try {
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				String query = prop.getProperty("query1").toString();
				queryOutput = QueryOutput.UTENTE;
				new QueryMaker(query, queryOutput);
				//String query2 = prop.getProperty("query2").toString();
			}
		});

		// Add another menu item using a Runnable.
				genericMenu.addMenuItem("2", "Esequi la seconda query", new Runnable() {
					@Override
					public void run() {
						System.out.println("Eseguo la seconda query");
						System.out.println("------Risultato-------");
						
						QueryOutput queryOutput;
						
						Properties prop = new Properties();
						try {
							prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						String query = prop.getProperty("query2").toString();
						queryOutput = QueryOutput.OFFICINA;
						new QueryMaker(query, queryOutput);
					}
				});
				
				// Add another menu item using a Runnable.
				genericMenu.addMenuItem("3", "Esequi la terza query", new Runnable() {
					@Override
					public void run() {
						System.out.println("Eseguo la terza query");
						System.out.println("------Risultato-------");
						
						QueryOutput queryOutput;
						
						Properties prop = new Properties();
						try {
							prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						String query = prop.getProperty("query3").toString();
						queryOutput = QueryOutput.UTENTE;
						new QueryMaker(query, queryOutput);
					}
				});
				// Add another menu item using a Runnable.
				genericMenu.addMenuItem("4", "Esequi la quarta query", new Runnable() {
					@Override
					public void run() {
						System.out.println("Eseguo la quarta query");
						System.out.println("------Risultato-------");
						
						QueryOutput queryOutput;
						
						Properties prop = new Properties();
						try {
							prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						String query = prop.getProperty("query4").toString();
						queryOutput = QueryOutput.UTENTE;
						new QueryMaker(query, queryOutput);
					}
				});
				
				// Add another menu item using a Runnable.
				genericMenu.addMenuItem("5", "Esequi la quinta query", new Runnable() {
					@Override
					public void run() {
						System.out.println("Eseguo la quinta query");
						System.out.println("------Risultato-------");
						
						QueryOutput queryOutput;
						
						Properties prop = new Properties();
						try {
							prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						String query = prop.getProperty("query5").toString();
						queryOutput = QueryOutput.UTENTE;
						new QueryMaker(query, queryOutput);
					}
				});
				// Add another menu item using a Runnable.
				genericMenu.addMenuItem("6", "Esequi la sesta query", new Runnable() {
					@Override
					public void run() {
						System.out.println("Eseguo la sesta query");
						System.out.println("------Risultato-------");
						
						QueryOutput queryOutput;
						
						Properties prop = new Properties();
						try {
							prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						String query = prop.getProperty("query6").toString();
						queryOutput = QueryOutput.UTENTE;
						new QueryMaker(query, queryOutput);
					}
				});
				
				// Add another menu item using a Runnable.
				genericMenu.addMenuItem("7", "Esequi la settima query", new Runnable() {
					@Override
					public void run() {
						System.out.println("Eseguo la settima query");
						System.out.println("------Risultato-------");
						
						QueryOutput queryOutput;
						
						Properties prop = new Properties();
						try {
							prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						String query = prop.getProperty("query7").toString();
						queryOutput = QueryOutput.UTENTE;
						new QueryMaker(query, queryOutput);
					}
				});
		// Finally initalize the menu
		genericMenu.initMenu();

		


	}
}

