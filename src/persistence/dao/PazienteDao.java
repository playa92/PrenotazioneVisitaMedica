package persistence.dao;

import java.util.List;
import model.Paziente;


public interface PazienteDao {

	public void save(Paziente paziente);
	public Paziente findByPrimaryKey(String codiceFiscale);
	public List<Paziente> findAll();
	public void update(Paziente paziente);
	public void delete(Paziente paziente);
	public boolean exists(Long matricola);
}
