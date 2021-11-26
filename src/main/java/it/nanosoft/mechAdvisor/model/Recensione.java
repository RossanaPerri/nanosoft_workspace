package it.nanosoft.mechAdvisor.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Recensione {
	private String id;
	private String titolo;
	private Double voto;
	private String nomeOfficina;

	public Recensione(ResultSet rs) throws SQLException {
		super();
		this.id = rs.getString("id");
		this.titolo = rs.getString("titolo");
		this.voto = rs.getDouble("voto");
		this.nomeOfficina = rs.getString("nome");
	}

	public Recensione() {
		super();
		this.id = "";
		this.titolo = "";
		this.voto = 0.0;
		this.nomeOfficina = "";
	}

	public Recensione(String id, String titolo, Double voto, String nomeOfficina) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.voto = voto;
		this.nomeOfficina = nomeOfficina;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Double getVoto() {
		return voto;
	}

	public void setVoto(Double voto) {
		this.voto = voto;
	}

	public String getNomeOfficina() {
		return nomeOfficina;
	}

	public void setNomeOfficina(String nomeOfficina) {
		this.nomeOfficina = nomeOfficina;
	}

	@Override
	public String toString() {
		return "Recensione = " + id + " " + titolo + " " + voto + " " + nomeOfficina;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, voto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeOfficina, other.nomeOfficina)
				&& Objects.equals(titolo, other.titolo) && Objects.equals(voto, other.voto);
	}
}
