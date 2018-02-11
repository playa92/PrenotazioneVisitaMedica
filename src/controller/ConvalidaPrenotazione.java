package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CodiceQR;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
import persistence.dao.PrenotazioneDao;


@SuppressWarnings("serial")
public class ConvalidaPrenotazione extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hexcode = request.getParameter("hexcode").toUpperCase();
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
				getDaoFactory().getCodiceQRDao();
		
		CodiceQR codiceQR = codiceQRDao.findByPrimaryKey(hexcode);
		PrenotazioneDao p = DatabaseManager.getInstance().getDaoFactory().getPrenotazioneDao();
		
		if(codiceQR == null) {
			response.getWriter().write("Non e' stata trovata alcuna prenotazione con il codice: " + hexcode);
		} 
		else 
			if(codiceQR.isConvalida()) {
			response.getWriter().write("Prenotazione gia' convalidata");
			
		} else {
			String importo = String.valueOf(p.findByPrimaryKey(hexcode).getImporto());
			codiceQR.setConvalida(true);
			codiceQRDao.update(codiceQR);
			response.getWriter().write("true;Prenotazione convalidata con successo;"+importo);		
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}