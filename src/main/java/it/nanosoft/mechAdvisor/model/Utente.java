package it.nanosoft.mechAdvisor.model;

public class Utente {
	private String nome;
	private String cognome;
//	private String email;
	
	public Utente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return "Utente = " + nome + " " + cognome;
	}
}
