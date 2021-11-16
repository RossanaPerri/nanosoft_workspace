package it.nanosoft;

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
		String sql;
		// 1) Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto primaria era un AUDI
		System.out.println("Tutti gli utenti che hanno cambiato auto negli ultimi 2 anni la cui auto primaria era un AUDI");
		sql = "SELECT A.nome, A.cognome, U.email "
				+ "FROM mech.utente as U, mech.anagrafica as A, mech.propieta_veicolo as PV, mech.veicolo as V "
				+ "WHERE A.email_utente = U.email and PV.email_utente = U.email and PV.id_veicolo = V.id "
				+ "and PV.tipologia_veicolo = V.tipologia and PV.data_fine_prop <= '2019-11-11' and V.marca ='audi'";
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(sql, queryOutput);	

		// 2) Le migliori 10 officine, calcolando il voto medio di ogni officina
		System.out.println("Le migliori 10 officine, calcolando il voto medio di ogni officina");
		sql = "SELECT o.nome "
				+ "FROM mech.officina o "
				+ "WHERE votomedio notnull "
				+ "ORDER BY votomedio desc "
				+ "limit 10";
		queryOutput = QueryOutput.OFFICINA;
		qm = new QueryMaker(sql, queryOutput);

		// 3)Tutti gli utenti della provincia di CZ che hanno effettuato almeno una sostituzione
		System.out.println("Tutti gli utenti della provincia di CZ che hanno effettuato almeno una sostituzione");
		sql = "SELECT a.nome, a.cognome "
				+ "FROM mech.utente u, mech.anagrafica a, mech.officina o, mech.tipologia_officina to2, mech.tipologia t, "
				+ "mech.servizio s, mech.commento c "
				+ "WHERE u.email = a.email_utente and a.provincia = 'catanzaro' and o.email_utente = u.email "
				+ "and to2.id_officina = o.id and t.tipo_officina = 'gommista' "
				+ "and s.nome = to2.tipo_officina_tipoligia and u.email = c.email_utente";
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(sql, queryOutput);

		// 4) Tutti i meccanici che possiedono le officine migliori
		System.out.println("Tutti i meccanici che possiedono le officine migliori");
		sql = "SELECT distinct a.nome, a.cognome "
				+ "FROM mech.utente u, mech.officina o, mech.inclusione i, mech.gruppo g, mech.anagrafica a "
				+ "WHERE u.email = o.email_utente and u.email = i.email_utente and g.id = i.id_gruppo "
				+ "and u.email = a.email_utente and g.descrizione = 'meccanico' and o.votomedio >=6";
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(sql, queryOutput);

		// 5) Gli utenti di tipo admin che sono anche utenti dell'applicazione che hanno lasciato 
		//		almeno una recensione positiva ( il cui valore è >=6 )
		System.out.println("Gli utenti di tipo admin che sono anche utenti dell'applicazione che hanno lasciato\n"
				+ "almeno una recensione positiva ( il cui valore è >=6 )");
		sql = "SELECT u.email, a.nome, a.cognome "
				+ "FROM mech.utente u, mech.anagrafica a, mech.recensioni r, mech.voto v, "
				+ "mech.gruppo g, mech.inclusione i "
				+ "WHERE r.email_utente = u.email and u.email = a.email_utente and r.id_voto = v.id "
				+ "and u.email = i.email_utente and g.id = i.id_gruppo and g.descrizione = 'admin' "
				+ "and v.voto >=6";
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(sql, queryOutput);

//		// 6) I 2 utenti che hanno lasciato più recensioni negative
//		System.out.println("I 2 utenti che hanno lasciato più recensioni negative");
//		sql = "SELECT a.nome, a.cognome, count(*) as c"
//				+ "FROM mech.utente u, mech.anagrafica a, mech.recensioni r, mech.voto v "
//				+ "WHERE r.email_utente = u.email and u.email = a.email_utente and r.id_voto = v.id and v.voto < 6 "
//				+ "group by a.nome, a.cognome, u.email "
//				+ "order by c desc limit 2";
//		queryOutput = QueryOutput.UTENTE;
//		qm = new QueryMaker(sql, queryOutput);

		// 8) Tutti gli utenti che in possesso di un motorino che hanno lasciato almeno 
		// 		una recensione negativa nella provincia di CS
		System.out.println("Tutti gli utenti che in possesso di un motorino che hanno lasciato almeno\n"
				+ "una recensione negativa nella provincia di CS");
		sql = "SELECT distinct a.nome, a.cognome "
				+ "FROM mech.utente u, mech.propieta_veicolo pv, mech.veicolo v, mech.voto v2, "
				+ "mech.officina o, mech.recensioni r, mech.anagrafica a "
				+ "WHERE u.email =  a.email_utente and o.email_utente = u.email and u.email = pv.email_utente and pv.id_veicolo = v.id "
				+ "and r.email_utente = u.email and r.id_veicolo = v.id and r.id_voto = v2.id "
				+ "and v2.voto < 6 and o.provincia = 'Cosenza' and v.tipologia = 'moto'";
		queryOutput = QueryOutput.UTENTE;
		qm = new QueryMaker(sql, queryOutput);
	}
}

