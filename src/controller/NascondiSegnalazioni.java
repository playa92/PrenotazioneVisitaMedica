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
public class NascondiSegnalazioni extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SegnalazioneDao segnalazioneDao = DatabaseManager.getInstance().getDaoFactory().getSegnalazioneDao();
	    List<Segnalazione> segnalazioni = segnalazioneDao.findAll();
		  
	    Boolean tmp = true;
	    Boolean first = true;
	    for(Segnalazione s:segnalazioni) {
	    	
	    	if(s.getRisolto()) {
	    		
	    		if(first) { 
	    			tmp = s.getMostra(); 
	    			first = false;
	    		}
	    		
	    		if(tmp) {
	    			s.setMostra(false);
	    			segnalazioneDao.update(s);
	    		}
	    		else {
	    			s.setMostra(true);
	    			segnalazioneDao.update(s);
	    		}
	    	}
	    }
  
	    if(segnalazioni.size() > 0) 
	      request.setAttribute("segnalazioni", segnalazioni);
	    else
	      request.setAttribute("vuoto", true);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("html/segnalazioni.jsp");
	    dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
