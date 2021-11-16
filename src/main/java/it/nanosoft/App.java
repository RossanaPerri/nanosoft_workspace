package it.nanosoft;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.standard.DateTimeAtCompleted;

import it.nanosoft.mechAdvisor.connection.PostgreSqlConnection;
import it.nanosoft.mechAdvisor.services.QueryServices;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		QueryServices qs = new QueryServices();
		
				
		String query = "select A.nome, A.cognome, U.email \r\n"
				+ "from utente as U, anagrafica as A, propieta_veicolo as PV, veicolo as V	\r\n"
				+ "where A.email_utente = U.email and PV.email_utente = U.email and PV.id_veicolo = V.id \r\n"
				+ "	and PV.tipologia_veicolo = V.tipologia and PV.data_fine_prop <= '2019-11-11' and V.marca ='audi';";
		
		
		qs.executeQuery(query);
	}
}

		

	
