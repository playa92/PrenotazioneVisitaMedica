package persistence;

public class DatabaseManager {

	private static DatabaseManager database = null;
	private DaoFactory daoFactory;
	
	private DatabaseManager() {
		daoFactory = DaoFactory.getDaoFactory(DaoFactory.POSTGRESQL);
	}
	
	//Singleton Pattern
	public static DatabaseManager getInstance() {
		
		if(database == null) { 
			database = new DatabaseManager();
		}
		return database;
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}
}
