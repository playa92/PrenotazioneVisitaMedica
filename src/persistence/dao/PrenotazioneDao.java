package persistence.dao;

import java.util.List;
import model.Prenotazione;

public interface PrenotazioneDao {

	public void save(Prenotazione prenotazione);
	public Prenotazione findByPrimaryKey(String codice);
	public List<Prenotazione> findAll();
	public void update(Prenotazione prenotazione);
	public void delete(Prenotazione prenotazione);
	public int getTotalVisits();
}
