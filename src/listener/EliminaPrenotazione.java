package listener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.Prenotazione;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
import persistence.dao.PrenotazioneDao;

@WebListener
public class EliminaPrenotazione implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servlet) {
		PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance()
				.getDaoFactory().getPrenotazioneDao();
		List<Prenotazione> prenotazioni = prenotazioneDao.findAll();
		
		if(!prenotazioni.isEmpty()) {
		
			Date currentTime = new Date();
			currentTime.setTime(Calendar.getInstance().getTimeInMillis());
			
			int indexOf = currentTime.toString().indexOf(":") - 2;
			String scadenza = currentTime.toString().substring(indexOf, indexOf + 8);
			
			CodiceQRDao codiceQRDao = DatabaseManager.getInstance()
					.getDaoFactory().getCodiceQRDao();
			
			for(Prenotazione it:prenotazioni) {
				if(it.getOrarioVisita().compareTo(scadenza) < 0 && 
						!codiceQRDao.findByPrimaryKey(it.getCodiceVisita()).isConvalida()) {
					prenotazioneDao.delete(it);
				}
			}
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servlet) {
		this.contextInitialized(servlet);
	}
}
