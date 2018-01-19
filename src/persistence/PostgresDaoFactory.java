package persistence;

import persistence.dao.AmministratoreDao;
import persistence.dao.CodiceQRDao;
import persistence.dao.PazienteDao;
import persistence.dao.UniversitaDao;
import persistence.dao.VisitaMedicaDao;

public class PostgresDaoFactory extends DaoFactory {

	private static  DataSource dataSource = null;
	
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in un file di configurazione!!!	
//			dataSource = new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
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
	public AmministratoreDao getAmministratoreDao() {
		return new AmministratoreDaoJDBC(dataSource);
	}

	@Override
	public UniversitaDao getUniversitaDao() {
		return new UniversitaDaoJDBC(dataSource);
	}

	@Override
	public CodiceQRDao getCodiceQR() {
		return new CodiceQRDaoJDBC(dataSource);
	}

	@Override
	public VisitaMedicaDao getVisitaMedica() {
		return new VisitaMedicaDaoJDBC(dataSource);
	}
	
	@Override
	public UtilDao getUtilDao() {
		return new UtilDao(dataSource);
	}
}
