package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.Amministratore;
import model.Impiegato;
import model.Paziente;
import persistence.DaoFactory;
import persistence.PersistenceException;
import persistence.UtilDao;
import persistence.dao.AmministratoreDao;
import persistence.dao.ImpiegatoDao;
import persistence.dao.UniversitaDao;

public class MainJDBC {

	protected static String[] readCredential() {
		
		final String[] credential = new String[4];
		try {
			FileReader f = new FileReader("./test/loginCredentials.txt"); 
		
			BufferedReader b = new BufferedReader(f);
			String line = b.readLine();
			int i = 0;
			
			while(line != null) {

				if(!line.equals("ADMIN:") && !line.equals("EMPLOYEE:")) {
					credential[i ++] = line;
				}
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
		
		ImpiegatoDao impiegatoDao = factory.getImpiegatoDao();
		AmministratoreDao amministratoreDao = factory.getAmministratoreDao();
		
		//CREAZIONE AMMINISTRATORI
		int count = 0;
		Long[] id = {new Long(1), new Long(2)}; 
		String[] r = {"sportello", "sistema"};
		for(String it : MainJDBC.readCredential()) {
		
			if(count < 2) {
				Amministratore admin = new Amministratore();
				String[] curr = it.split(":");
	
				admin.setUsername(curr[0]);
				admin.setPassword(curr[1]);	
				amministratoreDao.save(admin);
			} else {
				Impiegato imp = new Impiegato();
				String[] curr = it.split(":");
				
				imp.setId(id[count % 2]);
				imp.setUsername(curr[0]);
				imp.setPassword(curr[1]);
				imp.setRuolo(r[count % 2]);
				impiegatoDao.save(imp);
			}
			count ++;
		}
		
		//CREAZIONE PAZIENTI UNIVERSITARI
		UniversitaDao universitaDao = factory.getUniversitaDao();
		Paziente p1 = new Paziente();
		p1.setCodiceFiscale("CSNGNN94S27H579E");
		p1.setNome("Giovanni");
		p1.setCognome("Cosentino");
		p1.setMatricola(new Long(161782));
		
		universitaDao.save(p1);
		
		Paziente p2 = new Paziente();
		p2.setCodiceFiscale("LAODVD92S07");
		p2.setNome("Davide");
		p2.setCognome("Aloia");
		p2.setMatricola(new Long(164889));
	}
}
