package persistence.dao;

import java.util.List;
import model.Prenotazione;

public interface PrenotazioneDao {

	public void save(Prenotazione visitaMedica);
	public Prenotazione findByPrimaryKey(String codice);
	public List<Prenotazione> findAll();
	public void update(Prenotazione visitaMedica);
	public void delete(Prenotazione visitaMedica);
	public int getTotalVisits();
}
