package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Prenotazione;
import model.Segnalazione;
import persistence.DatabaseManager;
import persistence.dao.PrenotazioneDao;
import persistence.dao.SegnalazioneDao;

@SuppressWarnings("serial")
public class RestituisciSegnalazioni extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		SegnalazioneDao dao = DatabaseManager.getInstance().
				getDaoFactory().getSegnalazioneDao();
		List<Segnalazione> segnalazioni = dao.findAll();	

		//TMP
		HashMap<String, Segnalazione> tmp = new HashMap<>();
		for(Segnalazione s:segnalazioni) {
			tmp.put(s.getMotivazione(), s);
		}
		List<Segnalazione> tmp2 = new LinkedList<>();
		tmp2.addAll(tmp.values());
	
		if(segnalazioni.size() > 0)
			request.setAttribute("segnalazioni", tmp2);
		else
			request.setAttribute("vuoto", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/assistenza.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
