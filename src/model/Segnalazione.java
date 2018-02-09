package model;

public class Segnalazione {

	private Integer id;
	private String email;
	private String nomeUtente;
	private String motivazione;
	private String domanda;
	private String risposta;
	private Boolean risolto;
	
	public Segnalazione() {}
	
	public Segnalazione(Integer id, String email, String nomeUtente, String motivazione, 
			String domanda, String risposta, Boolean risolto) {
		
		this.id = id;
		this.email = email;
		this.nomeUtente = nomeUtente;
		this.motivazione = motivazione;
		this.risposta = risposta;
		this.domanda = domanda;
		this.risolto = risolto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public Boolean getRisolto() {
		return risolto;
	}

	public void setRisolto(Boolean risolto) {
		this.risolto = risolto;
	}
}
