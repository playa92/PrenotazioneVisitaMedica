package model;

public class Segnalazione {

	private Integer codice;
	private String nomeUtente;
	private String cognomeUtente;
	private String motivazione;
	private String risposta;
	
	public Segnalazione() {}
	
	public Segnalazione(Integer codice, String nomeUtente, String cognomeUtente, String motivazione, String risposta) {
		
		this.codice = codice;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
		this.motivazione = motivazione;
		this.risposta = risposta;
	}

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
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
}
