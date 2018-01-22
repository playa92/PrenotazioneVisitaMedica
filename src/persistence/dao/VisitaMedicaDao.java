package persistence.dao;

import java.util.List;
import model.Paziente;

public interface VisitaMedicaDao {

	public void save(Paziente paziente);
	public Paziente findByPrimaryKey(String codiceFiscale);
	public List<Paziente> findAll();
	public void update(Paziente paziente);
	public void delete(Paziente paziente);
}
