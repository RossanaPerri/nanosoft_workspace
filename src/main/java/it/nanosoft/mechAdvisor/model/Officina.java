package it.nanosoft.mechAdvisor.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Officina {

	private String nome;

	/**
	 * costruttore che crea una nuovo oggetto Officina a partire dal risultato di
	 * una query
	 * 
	 * @param rs risultato di una query
	 * @throws SQLException possibile errore di accesso al database o altri errori.
	 */
	public Officina(ResultSet rs) throws SQLException {
		nome = rs.getString("nome");
	}

	/**
	 * costruttore di default
	 */
	public Officina() {
		super();
		this.nome = "";
	}

	/**
	 * costruttore che crea un nuovo oggetto Officina inzializzando il campo nome
	 * 
	 * @param nome Stringa con cui si inzializza il campo nome
	 */
	public Officina(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Officina = " + nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Officina other = (Officina) obj;
		return Objects.equals(nome, other.nome);
	}
}
