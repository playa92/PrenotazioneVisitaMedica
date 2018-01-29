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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codice = request.getParameter("hex_code");
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
				getDaoFactory().getCodiceQRDao();
		
//		System.out.println("codice: " + codice);
		
		CodiceQR c = codiceQRDao.findByPrimaryKey(codice);
//		System.out.println(c.getScadenza());

		if(c != null) {
			
			String parseData = String.valueOf(c.getScadenza());
			System.out.println("data: " + parseData);
			response.getWriter().write("true;"+parseData);
			
		} else {
			response.getWriter().write("false;codice non valido o non trovato");
		}
		//D3AC7E46DD51

	}
}
