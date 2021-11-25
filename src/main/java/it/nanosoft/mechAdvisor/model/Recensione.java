package it.nanosoft.mechAdvisor.model;

import java.util.Objects;

public class Recensione {
	private String id;
	private Double voto;
	
	public Recensione() {
		super();
		this.id = "";
		this.voto = 0.0;
	}
	public Recensione(String id, Double voto) {
		this.id = id;
		this.voto = voto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Double getVoto() {
		return voto;
	}
	public void setVoto(Double voto) {
		this.voto = voto;
	}
	
	@Override
	public String toString() {
		return "Recensione = " + id + " " + voto;
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
		return Objects.equals(id, other.id) && Objects.equals(voto, other.voto);
	}
	
	
	
}