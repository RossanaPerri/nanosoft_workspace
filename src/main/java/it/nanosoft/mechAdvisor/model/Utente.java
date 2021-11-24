package it.nanosoft.mechAdvisor.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Utente {

	private String nome;
	private String cognome;

	/**
	 * costruttore che crea un nuovo oggetto Utente a partire dal risultato di una
	 * query
	 * 
	 * @param rs risultato di una query
	 * @throws SQLException possibile errore di accesso al database o altri errori.
	 */
	public Utente(ResultSet rs) throws SQLException {
		nome = rs.getString("nome");
		cognome = rs.getString("cognome");
	}

	/**
	 * costruttore di default
	 */
	public Utente() {
		super();
		this.nome = "";
		this.cognome = "";
	}

	/**
	 * costruttore che crea un nuovo oggetto Utente inzializzando il campo nome
	 * 
	 * @param nome    Stringa con cui si inzializza il campo nome
	 * @param cognome Stringa con cui si inzializza il campo cognome
	 */
	public Utente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	@Override
	public String toString() {
		return "Utente = " + nome + " " + cognome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(nome, other.nome);
	}
}
