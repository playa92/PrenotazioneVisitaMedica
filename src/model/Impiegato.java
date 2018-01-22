package model;

public class Impiegato {

	private Long id;
	private String nome;
	private String ruolo;
	
	public Impiegato() {}
	
	public Impiegato(Long id, String nome, String ruolo) {
		
		this.id = id;
		this.nome = nome;
		this.ruolo = ruolo;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
