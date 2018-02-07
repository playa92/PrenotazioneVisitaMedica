package persistence.dao;

import java.util.List;
import model.Paziente;

public interface UniversitaDao {

	public void save(Paziente paziente);
	public Paziente findByPrimaryKey(Long matricola);
	public List<Paziente> findAll();
}
