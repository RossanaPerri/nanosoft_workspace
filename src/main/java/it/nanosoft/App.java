package it.nanosoft;

import java.io.IOException;
import java.util.Properties;

import it.nanosoft.mechAdvisor.queryService.QueryMaker;
import it.nanosoft.mechAdvisor.queryService.QueryMaker.QueryOutput;

/**
 * 
 * @author Rossana Perri
 *
 */
public class App {

	public static void main(String[] args)  {
		
		QueryMaker qm;
		QueryOutput queryOutput;
		
		Properties prop = new Properties();
		try {
			prop.load(App.class.getClassLoader().getResourceAsStream("query.list"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("1) Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto primaria era un AUDI");
		String query1 = prop.getProperty("query1").toString();
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query1, queryOutput);
		
		System.out.println("2) Le migliori 10 officine, calcolando il voto medio di ogni officina");
		String query2 = prop.getProperty("query2").toString();
		queryOutput = QueryOutput.OFFICINA;
		qm = new QueryMaker(query2, queryOutput);
		
		System.out.println("3) Tutti gli utenti della provincia di CZ che hanno effettuato almeno una sostituzione");
		String query3 = prop.getProperty("query3").toString();
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query3, queryOutput);
		
		System.out.println("4) Tutti i meccanici che possiedono le officine migliori");
		String query4 = prop.getProperty("query4").toString();
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query4, queryOutput);
		
		System.out.println("5) Gli utenti di tipo admin che sono anche utenti dell'applicazione che hanno lasciato "
				+ "almeno una recensione positiva ( il cui valore è >=6 )");
		String query5 = prop.getProperty("query5").toString();
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query5, queryOutput);
	
		System.out.println("6) I 2 utenti che hanno lasciato più recensioni negative");
		String query6 = prop.getProperty("query6").toString();
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query6, queryOutput);
		
		System.out.println("7) Tutti gli utenti che in possesso di un motorino che hanno lasciato almeno "
				+ "una recensione negativa nella provincia di CS");
		String query7 = prop.getProperty("query7").toString();
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(query7, queryOutput);
		
		qm.closeMaker();
	}
}

