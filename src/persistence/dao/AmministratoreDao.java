package persistence.dao;

import model.Amministratore;
import persistence.AmministratoreCredenziali;
import persistence.PersistenceException;

public interface AmministratoreDao {

	public void save(Amministratore amministratore) throws PersistenceException;
	public Amministratore findByPrimaryKey(String username) throws PersistenceException;
	public AmministratoreCredenziali findByPrimaryKeyCredential(String username) throws PersistenceException;
}
