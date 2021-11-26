package it.nanosoft.mechAdvisor.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Officina {

	private String nome;
	private String provincia;
	private List<Recensione> recensioni = new ArrayList<Recensione>();

	public Officina() {
		super();
		this.nome = "";
		this.provincia = "";
		this.recensioni = null;
	}

	public Officina(ResultSet rs) throws SQLException {
		nome = rs.getString("nome");
		provincia = rs.getString("provincia");
	}

	public Officina(String nome, String provincia, List<Recensione> recensioni) {
		super();
		this.nome = nome;
		this.provincia = provincia;
		this.recensioni = recensioni;
	}

	public Officina(String nome, String provincia, Double votoMedio) {
		super();
		this.nome = nome;
		this.provincia = provincia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public void aggiungiRecensione(Recensione rec) {
		this.recensioni.add(rec);
	}

	@Override
	public String toString() {
		return "Officina = " + nome + " " + provincia + " " + recensioni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, provincia, recensioni);
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
		return Objects.equals(nome, other.nome) && Objects.equals(provincia, other.provincia)
				&& Objects.equals(recensioni, other.recensioni);
	}

	public Double calcolaVotoMedio() {
		double sommaVoti = 0;
		for (Recensione rec : getRecensioni()) {
			sommaVoti += rec.getVoto();
		}
		return sommaVoti / getRecensioni().size();
	}
}
