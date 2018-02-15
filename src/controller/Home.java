package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Segnalazione;
import persistence.DatabaseManager;
import persistence.dao.SegnalazioneDao;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"", "/home"})
public class Home extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("numSegnalazioni", contaSegnalazioni());
		
		if(session.getAttribute("wrong") != null) {
			
			if(session.getAttribute("wrong").equals(false)) {
				session.setAttribute("popUp", false);
			} else {
				session.setAttribute("wrong", false);
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private int contaSegnalazioni() {
		
		SegnalazioneDao dao = DatabaseManager.getInstance().getDaoFactory().getSegnalazioneDao();
		List<Segnalazione> segnalazioni = dao.findAll();
		
		int cont = 0;
		for(Segnalazione s:segnalazioni)  {
			if(!s.getRisolto()) 
				cont ++;
		}
		return cont;
	}
}
