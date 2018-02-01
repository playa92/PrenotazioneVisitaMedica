package model;

//import java.util.Date;

public class CodiceQR {

	private String esadecimale;
	private Boolean valido;
//	private Date scadenza;
	private String scadenza;

	public CodiceQR() {}
	
	public CodiceQR(String esadecimale, String scadenza, Boolean valido) {
		
		this.esadecimale = esadecimale;
		this.valido = valido;
		this.scadenza = scadenza;
	}

	public String getCodice() {
		return esadecimale;
	}

	public void setCodice(String esadecimale) {
		this.esadecimale = esadecimale;
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
