package it.nanosoft.mechAdvisor.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Officina {
	
	private String nome;
	
	public Officina(ResultSet rs) throws SQLException {
		nome = rs.getString("nome");
	}

	public Officina() {
		super();
		this.nome = "";
	}

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
