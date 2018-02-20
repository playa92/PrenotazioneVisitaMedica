package persistence.dao;

import java.util.List;

import model.Accesso;

public interface AccessoDao {

	public void save(Accesso accesso);
	public List<Accesso> findAll();
	public void delete(Accesso accesso);
}
