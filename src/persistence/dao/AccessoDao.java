package persistence.dao;

import java.util.List;

import model.Accesso;

public interface AccessoDao {

	public int assignId();
	public void save(Accesso accesso);
	public List<Accesso> findAll();
	public void deleteAll();
}
