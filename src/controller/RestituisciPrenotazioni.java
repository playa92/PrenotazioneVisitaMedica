package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Prenotazione;
import persistence.DatabaseManager;
import persistence.dao.PrenotazioneDao;

@SuppressWarnings("serial")
public class RestituisciPrenotazioni extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		PrenotazioneDao dao =DatabaseManager.getInstance().
				getDaoFactory().getPrenotazioneDao();
		List<Prenotazione> prenotazioni = dao.findAll();
		request.setAttribute("prenotazioni", prenotazioni);
			
		System.out.println("-------->");
		for(Prenotazione p: prenotazioni) {
			System.out.println(p.toString());
		}
		
		RequestDispatcher dispacher = request.getRequestDispatcher("html/visualizza_prenotazioni.jsp");
		dispacher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
