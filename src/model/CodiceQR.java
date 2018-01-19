package model;

//import java.awt.Image;
import java.util.Date;

public class CodiceQR {

	private String codice;//esadecimale
	private Boolean valido;
	private Date scadenza;
	
//	private Image img; se vogliamo possiamo aggiungere l'immagine
	
	public CodiceQR() {}
	
	public CodiceQR(Date scadenza, String codice, Boolean valido) {
		
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

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
}
