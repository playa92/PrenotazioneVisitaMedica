package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CodiceQR;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;

@SuppressWarnings("serial")
public class RicercaPrenotazione extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String hexcode = request.getParameter("hexcode");
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
				getDaoFactory().getCodiceQRDao();
		CodiceQR codice = codiceQRDao.findByPrimaryKey(hexcode);
		
		if(codice != null) {	
//			response.getWriter().write("true;" + codice.getScadenza());
			response.getWriter().write("true;" + "12:00:00;07:20:00");

			
		} else {
			response.getWriter().write("false;Codice non trovato");
		}
	}

}
