package it.nanosoft;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.menu.GenericMenu;
import it.nanosoft.mechAdvisor.model.Utente;
import it.nanosoft.mechAdvisor.service.Loggable;
import it.nanosoft.mechAdvisor.service.QueryMaker;
import it.nanosoft.mechAdvisor.service.ReportMaker;

/**
 * Questa è la classe principale che serve per avviare il programma. Utilizza la classe del Menù
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
				Properties prop = new Properties();
				try {
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
					query = prop.getProperty("query1").toString();
					QueryMaker qm = new QueryMaker();
					List<Utente> userList = qm.makeQuery(query);	
					ReportMaker rp = new ReportMaker();
					rp.reportUtente(userList);
					qm.closeMaker();
				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				
				}
			}});

//		// Add another menu item using a Runnable.
//		genericMenu.addMenuItem("2", "Esequi la seconda query", new Runnable() {
//			public void run() {
//
//				String query = null;
//				newloggerApp.info("Eseguo la seconda query");
//				newloggerApp.info("------Risultato-------");
//
//
//				Properties prop = new Properties();
//				try {
//					newloggerApp.info("Carico il file delle query");
//					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
//				} catch (IOException e) {
//					newloggerApp.error("Impossibile caricare il file delle query" + e.getMessage());
//				}
//				try {
//					query = prop.getProperty("query2").toString();
//
//				} catch (Exception e) {
//					newloggerApp.error(e.getMessage());
//				}
//				new QueryMaker(query, "query2");
//			}
//		});

		
		// Finally initalize the menu
		genericMenu.initMenu();

	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}
