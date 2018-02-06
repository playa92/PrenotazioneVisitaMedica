package persistence.dao;

import model.Amministratore;

public interface AmministratoreDao {

	public void save(Amministratore amministratore);
	public Amministratore findByPrimaryKey(String username);
	public void update(Amministratore amministratore);
	public void delete(Amministratore amministratore);
}
