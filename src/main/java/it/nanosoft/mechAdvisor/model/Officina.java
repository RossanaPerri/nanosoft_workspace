package it.nanosoft.mechAdvisor.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Officina {
	
	private String nome;
	private List<Recensione> recensioni;
	private Recensione recensione;
	


	public Officina(ResultSet rs) throws SQLException {
		nome = rs.getString("nome");
		recensioni = new ArrayList<Recensione>();
		recensione = new Recensione(rs.getString("id"), rs.getDouble("voto"));
		aggiungiRecensione(recensione);
	}
	
	
	public void aggiungiRecensione(Recensione r) {
		recensioni.add(r);
	}

	
	public double getVotoMedio() {
		double voto = 0.0;
		int count = 0;
		for(Recensione r : recensioni) {
			voto += r.getVoto();
			count++;
			}
		return voto/count;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}



	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
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
		return "Officina [nome=" + nome + "]";
	}

	

}
