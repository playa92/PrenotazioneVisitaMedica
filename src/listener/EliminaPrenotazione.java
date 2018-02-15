package listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.CodiceQR;
import model.Paziente;
import model.Prenotazione;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
import persistence.dao.PazienteDao;
import persistence.dao.PrenotazioneDao;

@WebListener
public class EliminaPrenotazione implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servlet) {
		
		PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance().getDaoFactory().getPrenotazioneDao();
		List<Prenotazione> prenotazioni = prenotazioneDao.findAll();
		
		if(!prenotazioni.isEmpty()) {
		
			String dateFormat = "HH:mm";
			String scadenza = new SimpleDateFormat(dateFormat).format(new Date());
			
			CodiceQRDao codiceQRDao = DatabaseManager.getInstance().getDaoFactory().getCodiceQRDao();
			PazienteDao pazienteDao = DatabaseManager.getInstance().getDaoFactory().getPazienteDao();
			
			for(Prenotazione it:prenotazioni) {
				if(it.getOrarioVisita().compareTo(scadenza) < 0 && 
						!codiceQRDao.findByPrimaryKey(it.getCodiceVisita()).isConvalida()) {
					prenotazioneDao.delete(it);
					
					List<Paziente> pazienti = pazienteDao.findAll();
					Paziente paziente = findByIdVisit(pazienti, it.getCodiceVisita());
					pazienteDao.delete(paziente);
					
					CodiceQR codiceQR = codiceQRDao.findByPrimaryKey(it.getCodiceVisita());
					codiceQRDao.delete(codiceQR);
				}
			}
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servlet) {
		this.contextInitialized(servlet);
	}
	
	private Paziente findByIdVisit(List<Paziente> pazienti, String codice) {
		
		for(Paziente p:pazienti) {
			if(p.getCodiceQR().equals(codice)) {
				return p;
			}
		}
		return null;
	}
}
