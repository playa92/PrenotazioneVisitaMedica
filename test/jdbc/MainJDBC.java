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

		DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDao();
		util.dropDatabase();
		util.createDatabase();
		
		PazienteDao pazienteDao = factory.getPazienteDao();
		
		//CREATE
		List<Paziente> p = new ArrayList<>();
		
		Paziente p1 = new Paziente();
		p1.setNome("Giovanni");
		p1.setCognome("Cosentino");
		p1.setMatricola(new Long(001));
		p1.setInvalidita("PatologiaA");
		p.add(p1);
		
		Paziente p2 = new Paziente();
		p2.setNome("Davide");
		p2.setCognome("Aloia");
		p2.setMatricola(new Long(002));
		p2.setInvalidita("PatologiaB");
		p.add(p2);
		
		Paziente p3 = new Paziente();
		p3.setNome("Michele");
		p3.setCognome("Cangeri");
		p3.setMatricola(new Long(003));
		p3.setInvalidita("");
		p.add(p3);
		
		for(int i = 0; i < p.size(); i++) {
			pazienteDao.save(p.get(i));
		}
//		System.out.println("Finished");
	}
}
