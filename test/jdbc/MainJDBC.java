package jdbc;

import java.util.ArrayList;
import java.util.List;

import model.Paziente;
import persistence.DaoFactory;
import persistence.PersistenceException;
import persistence.UtilDao;
import persistence.dao.PazienteDao;

public class MainJDBC {

	public static void main(String[] args) throws PersistenceException {

		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDao();
		util.dropDatabase();
		util.createDatabase();
		
		PazienteDao pazienteDao = factory.getPazienteDao();
		
		//CREATE
		List<Paziente> p = new ArrayList<>();
		
		Paziente p1 = new Paziente();
		p1.setName("Giovanni");
		p1.setSurname("Cosentino");
		p1.setId(new Long(001));
		p1.setInvalidity("PatologiaA");
		p.add(p1);
		
		Paziente p2 = new Paziente();
		p2.setName("Davide");
		p2.setSurname("Aloia");
		p2.setId(new Long(002));
		p2.setInvalidity("PatologiaB");
		p.add(p2);
		
		Paziente p3 = new Paziente();
		p3.setName("Michele");
		p3.setSurname("Cangeri");
		p3.setId(new Long(003));
		p3.setInvalidity("Altro");
		p.add(p3);
		
		for(int i = 0; i < p.size(); i++) {
			pazienteDao.save(p.get(i));
		}
//		System.out.println("Finished");
	}
}
