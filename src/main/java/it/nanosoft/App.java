package it.nanosoft;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;

import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.menu.GenericMenu;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Utente;
import it.nanosoft.mechAdvisor.service.Loggable;
import it.nanosoft.mechAdvisor.service.MailWithAttachmentService;
import it.nanosoft.mechAdvisor.service.QueryMaker;
import it.nanosoft.mechAdvisor.service.ReportMaker;

/**
 * Questa è la classe principale che serve per avviare il programma. Utilizza la classe del Menù
 *
 * @author emicovi
 *
 */

public class App implements Loggable {
	public static void main(String[] args) throws MessagingException, IOException {

		GenericMenu genericMenu = new GenericMenu();
		
		MailWithAttachmentService mail = new  MailWithAttachmentService("covelliemanuel@gmail.com", "jlgpjkgrebazpkcp", "smtp.gmail.com", 587);
		
		mail.sendMail(mail.getSession());
		
		

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("1", "Esequi la prima query", new Runnable() {
			public void run() {
				String query = null;
				Properties prop = new Properties();
				try {
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
					query = prop.getProperty("query1").toString();
					QueryMaker qm = new QueryMaker();
					List<Utente> userList = qm.makeQueryUtente(query);	
					ReportMaker rm = new ReportMaker();
					rm.createUtenteReports(userList, "reportUtente_1.xlsx");
					qm.closeMaker();
				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				
				}
			}});
		
		genericMenu.addMenuItem("9", "Esequi la nona query", new Runnable() {
			public void run() {
				String query = null;
				Properties prop = new Properties();
				try {
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
					query = prop.getProperty("query9").toString();
					QueryMaker qm = new QueryMaker();
					List<Officina> officinaList = qm.makeQueryOfficina2(query);	
					ReportMaker rm = new ReportMaker();
					rm.createOfficinaReports(officinaList, "reportOfficina_9.xlsx");
					qm.closeMaker();
				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				
				}
			}});

		// Add another menu item using a Runnable.
		genericMenu.addMenuItem("3", "Esequi la terza query", new Runnable() {
			public void run() {
				String query = null;
				Properties prop = new Properties();
				try {
					prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
					query = prop.getProperty("query3").toString();
					QueryMaker qm = new QueryMaker();
					List<Utente> userList = qm.makeQueryUtente(query);	
					ReportMaker rm = new ReportMaker();
					rm.createUtenteReports(userList, "reportUtente_3.xlsx");
					qm.closeMaker();
				} catch (Exception e) {
					newloggerApp.error(e.getMessage());
				
				}
			}});


		
		// Finally initalize the menu
		genericMenu.initMenu();

	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}
