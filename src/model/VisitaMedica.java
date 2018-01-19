package model;

import java.util.ArrayList;
import java.util.List;

public class VisitaMedica {

	private List<Paziente> pazienti;
	
	public VisitaMedica() {
		pazienti = new ArrayList<>();
	}
	
	public List<Paziente> getPazienti() {
		return pazienti;
	}
	
	public Paziente getPaziente(int i) {
		return pazienti.get(i);
	}
	
	public void addPaziente(Paziente p) {
		pazienti.add(p);
	}
}
