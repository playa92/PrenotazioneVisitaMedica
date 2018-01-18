package persistence.dao;

import java.util.List;

import model.Paziente;
import persistence.PersistenceException;

public interface PazienteDao {

	public void save(Paziente paziente) throws PersistenceException;
	public Paziente findByPrimaryKey(Long id) throws PersistenceException;
	public List<Paziente> findAll() throws PersistenceException;
	public void update(Paziente paziente) throws PersistenceException;
	public void delete(Paziente paziente) throws PersistenceException;
}
