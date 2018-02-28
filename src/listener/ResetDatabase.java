package listener;

import persistence.DatabaseManager;
import persistence.UtilDao;

public class ResetDatabase implements Runnable {
	
	private UtilDao dao;
	
	@Override
	public void run() {
		
		dao = DatabaseManager.getInstance().getDaoFactory().getUtilDao();
		dao.resetDatabase();
	}
}
