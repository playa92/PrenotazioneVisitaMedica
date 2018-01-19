package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Amministratore;
import model.CodiceQR;
import model.Paziente;
import persistence.DaoFactory;
import persistence.PersistenceException;
import persistence.UtilDao;
import persistence.dao.AmministratoreDao;
import persistence.dao.PazienteDao;

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
		
		PazienteDao pazienteDao = factory.getPazienteDao();
		AmministratoreDao amministratoreDao = factory.getAmministratoreDao();
		
		//CREAZIONE PAZIENTI
		List<Paziente> p = new ArrayList<>();
		
		Calendar c = Calendar.getInstance();
		c.set(2018, Calendar.FEBRUARY, 2);
		Date scadenza = c.getTime();
		
		CodiceQR c1 = new CodiceQR();
		c1.setCodice("ABC123");
		c1.setScadenza(scadenza);
		
		Paziente p1 = new Paziente();
		p1.setNome("Giovanni");
		p1.setCognome("Cosentino");
		p1.setMatricola(new Long(161782));
		p1.setInvalidita("");
		p1.setCodice(c1);
		p.add(p1);
		
//		Calendar cal2 = Calendar.getInstance();
//		cal2.set(2018, Calendar.JANUARY, 31);
//		scadenza = cal2.getTime();//date
		
//		CodiceQR c2 = new CodiceQR();
//		c2.setCodice("DEF456");
//		c2.setScadenza(scadenza);
		
//		Paziente p2 = new Paziente();
//		p2.setNome("Davide");
//		p2.setCognome("Aloia");
//		p2.setMatricola(new Long(164889));
//		p2.setInvalidita("");
//		p2.setCodice(null);
//		p.add(p2);
//		
//		Paziente p3 = new Paziente();
//		p3.setNome("Michele");
//		p3.setCognome("Cangeri");
//		p3.setMatricola(null);
//		p3.setInvalidita("");
//		p3.setCodice(null);
//		p.add(p3);
		
		for(int i = 0; i < p.size(); i++) {
			pazienteDao.save(p.get(i));
		}
		
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
