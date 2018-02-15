package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Segnalazione;
import persistence.DatabaseManager;
import persistence.dao.SegnalazioneDao;

@SuppressWarnings("serial")
public class RisolviSegnalazione extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String risposta = request.getParameter("risposta");
	    String motivazione = request.getParameter("motivazione");
	    	    
	    SegnalazioneDao segnalazioneDao = DatabaseManager.getInstance().getDaoFactory().getSegnalazioneDao();
	    List<Segnalazione> segnalazioni = segnalazioneDao.findAll();
	    
	    for(Segnalazione s: segnalazioni) {
	    	
	    	if(s.getMotivazione().equals(motivazione) && !s.getRisolto()) {
	    		
	    		s.setRisposta(risposta);
	            s.setRisolto(true);
	            segnalazioneDao.update(s);
	            break;
	    	}
	    }
	    request.setAttribute("segnalazioni", segnalazioni);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("html/segnalazioni.jsp");
	    dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
