package persistence.dao;

import java.util.List;

import model.Logging;

public interface LoggingDao {

	public int assignId();
	public void save(Logging accesso);
	public List<Logging> findAll();
	public void deleteAll();
}
