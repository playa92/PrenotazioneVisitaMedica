package model;

public class Paziente {

	private String codiceFiscale;
	private String nome;
	private String cognome;
	private Long matricola;
	private String invalidita;
	private CodiceQR codice;
	private Double importo;
	
	public Paziente() {}
	
	public Paziente(String codiceFiscale, String nome, String cognome, Long matricola, String invalidita, 
			CodiceQR codice, Double importo) {
		
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.invalidita = invalidita;
		this.codice = codice;
		this.importo = importo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Long getMatricola() {
		return matricola;
	}

	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}

	public String getInvalidita() {
		return invalidita;
	}

	public void setInvalidita(String invalidita) {
		this.invalidita = invalidita;
	}

	public CodiceQR getCodiceQR() {
		return codice;
	}

	public void setCodiceQR(CodiceQR codice) {
		this.codice = codice;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}
}
