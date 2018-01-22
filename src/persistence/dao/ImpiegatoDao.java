package persistence.dao;

import model.Impiegato;

public interface ImpiegatoDao {

	public void save(Impiegato impiegato);
	public void update(Impiegato impiegato);
	public void delete(Impiegato impiegato);
}
