package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CodiceQR;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
import persistence.dao.PrenotazioneDao;

@SuppressWarnings("serial")
public class ConvalidaPrenotazione extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loggato").equals(true)) {
		
			String hexcode = request.getParameter("hexcode");
			CodiceQRDao codiceQRDao = DatabaseManager.getInstance().getDaoFactory().getCodiceQRDao();
			
			CodiceQR codiceQR = codiceQRDao.findByPrimaryKey(hexcode);
			PrenotazioneDao p = DatabaseManager.getInstance().getDaoFactory().getPrenotazioneDao();
			
			if(codiceQR == null) {
				response.getWriter().write("Non è stata trovata alcuna prenotazione con il codice: " + hexcode);
			} 
			else 
				if(codiceQR.isConvalida()) {
					response.getWriter().write("Prenotazione già convalidata");
					
				} else {
					String importo = String.valueOf(p.findByPrimaryKey(hexcode).getImporto());
					codiceQR.setConvalida(true);
					codiceQRDao.update(codiceQR);
					response.getWriter().write("true;Prenotazione convalidata con successo;" + importo);		
			}
			return;
		}
		response.sendError(404, "Effettuare l'accesso come admin o come employee per visualizzare quest'area");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}