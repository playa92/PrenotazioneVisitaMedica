package persistence;

import persistence.dao.AmministratoreDao;
import persistence.dao.CodiceQRDao;
import persistence.dao.ImpiegatoDao;
import persistence.dao.LoggingDao;
import persistence.dao.UniversitaDao;
import persistence.dao.PazienteDao;
import persistence.dao.PrenotazioneDao;
import persistence.dao.SegnalazioneDao;

public abstract class DaoFactory {

	public static final int HSQLDB = 1;
	public static final int POSTGRESQL = 2;
	
	public static DaoFactory getDaoFactory(int whichFactory) {
		
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
	
	public abstract AmministratoreDao getAmministratoreDao();
	
	public abstract UniversitaDao getUniversitaDao();
	
	public abstract CodiceQRDao getCodiceQRDao();
	
	public abstract PrenotazioneDao getPrenotazioneDao();
	
	public abstract ImpiegatoDao getImpiegatoDao();
	
	public abstract SegnalazioneDao getSegnalazioneDao();
	
	public abstract LoggingDao getLoggingDao();
	
	public abstract UtilDao getUtilDao();
}
