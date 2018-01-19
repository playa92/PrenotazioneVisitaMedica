package persistence.dao;

import java.util.List;
import model.Paziente;


public interface PazienteDao {

	public void save(Paziente paziente);
	public Paziente findByPrimaryKey(Long id);
	public List<Paziente> findAll();
	public void update(Paziente paziente);
	public void delete(Paziente paziente);
}
