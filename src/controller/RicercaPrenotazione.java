package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CodiceQR;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;

@SuppressWarnings("serial")
public class RicercaPrenotazione extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		PrintWriter out = resp.getWriter();
		String codice = req.getParameter("hex");
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().getDaoFactory().getCodiceQRDao();
		
//		System.out.println("codice: " + codice);
		
//		CodiceQR c = codiceQRDao.findByPrimaryKey(codice);
//		System.out.println(c.getScadenza());
//
//		if(c != null) {
//			
//			String parseData = String.valueOf(c.getScadenza());
//			System.out.println("data: " + parseData);
//			response.getWriter().write("true;"+parseData);
//			
//		} else {
//			response.getWriter().write("false;codice non valido o non trovato");
//		}

	
		resp.setContentType("text/html;charset=UTF-8");

	    try { 
	        out.println("20:03:44");
	    } finally {
	       out.close();
	    }
	
//		D3AC7E46DD51
	}

}
