package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Amministratore;
import model.Impiegato;
import model.Segnalazione;
import persistence.DatabaseManager;
import persistence.dao.AmministratoreDao;
import persistence.dao.ImpiegatoDao;
import persistence.dao.SegnalazioneDao;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//disconnessione
		if((request.getParameter("logout") != null) && (request.getParameter("logout").equals("true"))) {
			session.setAttribute("popUp", false);
			session.setAttribute("username", null);
			session.setAttribute("loggato", false);
			
			if(session.getAttribute("loggatoAdmin").equals(true)) {
				session.setAttribute("loggatoAdmin", false);
			} else {
				session.setAttribute("loggatoEmployee", false);
			}	
		    response.sendRedirect("home");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
		session.setAttribute("username", null);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AmministratoreDao amministratoreDao = DatabaseManager.getInstance().getDaoFactory().getAmministratoreDao();
		Amministratore amministratore = amministratoreDao.findByPrimaryKey(username);
	
		if(amministratore == null) {
			
			ImpiegatoDao impiegatoDao = DatabaseManager.getInstance().getDaoFactory().getImpiegatoDao();
			Impiegato impiegato = impiegatoDao.findByPrimaryKey(username);
		
			if(impiegato == null) {
				
				session.setAttribute("popUp", true);
				session.setAttribute("wrong", true);//attributo che serve solo per non visualizzare il popUp
				session.setAttribute("popUpMessage", "Nessun utente registrato come " + username);	
			} else { 
				if(password.equals(impiegato.getPassword())) {
				
					session.setAttribute("numSegnalazioni", contaSegnalazioni());
					session.setAttribute("popUp", false);
					session.setAttribute("username", username);				
					session.setAttribute("loggato", true);
					session.setAttribute("loggatoEmployee", true);
					session.setAttribute("username", username);//JSTL
				} else {
					session.setAttribute("popUp", true);
					session.setAttribute("wrong", true);
					session.setAttribute("popUpMessage", "Spiacente, password non corrispondente per " + username);
				}
			}	
			
		} else { 
			//connessione
			if(password.equals(amministratore.getPassword())) {	
				
				session.setAttribute("numSegnalazioni", contaSegnalazioni());
				session.setAttribute("popUp", false);
				session.setAttribute("username", username);
				session.setAttribute("loggato", true);
				session.setAttribute("loggatoAdmin", true);
				session.setAttribute("username", username);//JSTL
								
			} else {
				session.setAttribute("popUp", true);
				session.setAttribute("wrong", true);
				session.setAttribute("popUpMessage", "Spiacente, password non corrispondente per " + username);
			}
		}
		response.sendRedirect("home");
	}
	
	private int contaSegnalazioni() {
		
		SegnalazioneDao dao = DatabaseManager.getInstance().getDaoFactory().getSegnalazioneDao();
		List<Segnalazione> segnalazioni = dao.findAll();
		
		int cont = 0;
		for(Segnalazione s:segnalazioni) {
			if(!s.getRisolto())
				cont ++;
		}
		return cont;
	}
}