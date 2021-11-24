package it.nanosoft.mechAdvisor.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Officina {
	
	private String nome;
	private Double voto;
	
	public Double getVoto() {
		return voto;
	}

	public void setVoto(Double voto) {
		this.voto = voto;
	}

	public Officina(ResultSet rs) throws SQLException {
		nome = rs.getString("nome");
		voto = rs.getDouble("voto");
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

	@Override
	public String toString() {
		return "Officina [nome=" + nome + ", voto=" + voto + "]";
	}

	public Officina(String nome, Double voto) {
		super();
		this.nome = nome;
		this.voto = voto;
	}

	

}
