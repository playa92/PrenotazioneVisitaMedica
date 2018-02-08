package model;

public class Segnalazione {

	private Integer id;
	private String nomeUtente;
	private String cognomeUtente;
	private String motivazione;
	private String commento;
	private String risposta;
	private Boolean risolto;
	
	public Segnalazione() {}
	
	public Segnalazione(Integer id, String nomeUtente, String cognomeUtente, String motivazione, 
			String commento, String risposta, Boolean risolto) {
		
		this.id = id;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
		this.motivazione = motivazione;
		this.risposta = risposta;
		this.commento = commento;
		this.risolto = risolto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getCognomeUtente() {
		return cognomeUtente;
	}

	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
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

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Boolean getRisolto() {
		return risolto;
	}

	public void setRisolto(Boolean risolto) {
		this.risolto = risolto;
	}
}
