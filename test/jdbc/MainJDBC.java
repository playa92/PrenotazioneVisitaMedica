package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.Amministratore;
import persistence.DaoFactory;
import persistence.PersistenceException;
import persistence.UtilDao;
import persistence.dao.AmministratoreDao;

public class MainJDBC {

	protected static String[] readCredential() {
		
		final String[] credential = new String[2];
		try {
			FileReader f = new FileReader("./test/loginCredential.txt"); 
		
			BufferedReader b = new BufferedReader(f);
			String line = b.readLine();
			int i = 0;
			
			while(line != null) {

				credential[i ++] = line;
				line = b.readLine();
			}
			b.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return credential;
	}
	
	public static void main(String[] args) throws PersistenceException {

		DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDao();
		util.dropDatabase();
		util.createDatabase();
		
//		ImpiegatoDao impiegatoDao = factory.getImpiegatoDao();
		AmministratoreDao amministratoreDao = factory.getAmministratoreDao();
		
		//CREAZIONE AMMINISTRATORI	
		for(String it : MainJDBC.readCredential()) {
		
			Amministratore admin = new Amministratore();
			String[] curr = it.split(":");
	
			admin.setUsername(curr[0]);
			admin.setPassword(curr[1]);	
			amministratoreDao.save(admin);
		}
	}
}
