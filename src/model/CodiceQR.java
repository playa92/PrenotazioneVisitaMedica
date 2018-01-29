package model;

//import java.util.Date;

public class CodiceQR {

	private String codice;//esadecimale
	private Boolean valido;
//	private Date scadenza;
	private String scadenza;

	public CodiceQR() {}
	
	public CodiceQR(String codice, String scadenza, Boolean valido) {
		
		this.codice = codice;
		this.valido = valido;
		this.scadenza = scadenza;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
}
