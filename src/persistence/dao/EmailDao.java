package persistence.dao;

import java.util.List;
import model.Email;

public interface EmailDao {
	
	public void save(Email email);
	public List<Email> findAll();
}
