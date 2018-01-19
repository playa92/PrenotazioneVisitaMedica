package persistence.dao;

import model.Amministratore;
import persistence.AmministratoreCredenziali;

public interface AmministratoreDao {

	public void save(Amministratore amministratore);
	public Amministratore findByPrimaryKey(String username);
	public AmministratoreCredenziali findByPrimaryKeyCredential(String username);
	public void delete(Amministratore amministratore);
}
