package it.nanosoft.mechAdvisor.model;

public class Officina {
	private String nome;

	public Officina(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Officina = " + nome;
	}
}
