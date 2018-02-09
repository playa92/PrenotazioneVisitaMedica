package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CodiceQR;
import model.Prenotazione;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
import persistence.dao.PrenotazioneDao;

@SuppressWarnings("serial")
public class RicercaPrenotazione extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String hexcode = request.getParameter("hexcode").toUpperCase();
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
				getDaoFactory().getCodiceQRDao();
		PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance().
				getDaoFactory().getPrenotazioneDao();
		CodiceQR codice = codiceQRDao.findByPrimaryKey(hexcode);
		
		if(codice != null) {	
		
			String current = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
			if(codice.getScadenza().compareTo(current) < 0) {
				response.getWriter().write("false;Prenotazione scaduta");
			} else {
			Prenotazione prenotazione = prenotazioneDao.findByPrimaryKey(codice.getCodice());
			response.getWriter().write("true;" + codice.getScadenza() + ";" + prenotazione.getOrarioVisita() + ";" + hexcode);
			}
			
		} else {
			response.getWriter().write("false;Codice non trovato");
		}
	}

}
