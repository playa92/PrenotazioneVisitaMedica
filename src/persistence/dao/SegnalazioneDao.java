package persistence.dao;

import java.util.List;

import model.Segnalazione;

public interface SegnalazioneDao {

	public void save(Segnalazione segnalazione);
	public List<Segnalazione> findAll();
	public void update(Segnalazione segnalazione);
	public void delete(Segnalazione segnalazione);
}
