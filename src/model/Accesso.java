package model;

import java.util.Date;

public class Accesso {

	private String azione;
	private Date data;
	private String orario;
	private String nomeUtente;
	
	public Accesso() {}
	
	public Accesso(String azione, Date data, String orario, String nomeUtente) {
		
		this.azione = azione;
		this.data = data;
		this.orario = orario;
		this.nomeUtente = nomeUtente;
	}

	public String getAzione() {
		return azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}	
}
