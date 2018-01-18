package persistence;

import persistence.dao.PazienteDao;

public abstract class DaoFactory {

	public static final int HSQLDB = 1;
	public static final int POSTGRESQL = 2;
	
	public static DaoFactory getDAOFactory(int whichFactory) {
		
		switch(whichFactory) {
		case HSQLDB:
			return null;
		case POSTGRESQL:
			return new PostgresDaoFactory();
		default:
			return null;
		}
	}
	
	public abstract PazienteDao getPazienteDao();
	
	public abstract UtilDao getUtilDao();
}
