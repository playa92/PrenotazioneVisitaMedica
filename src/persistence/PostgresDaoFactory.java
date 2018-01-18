package persistence;

import persistence.dao.PazienteDao;

public class PostgresDaoFactory extends DaoFactory {

	private static  DataSource dataSource = null;
	
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in un file di configurazione!!!	
			dataSource = new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource = new DataSource("jdbc:postgresql://localhost:5432/Prenotazione","postgres","postgres");
		
		} catch(Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
//			e.printStackTrace();
		}
	}

	@Override
	public PazienteDao getPazienteDao() {
		return new PazienteDaoJDBC(dataSource);
	}

	@Override
	public UtilDao getUtilDao() {
		return new UtilDao(dataSource);
	}
}
