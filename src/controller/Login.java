package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Amministratore;
import persistence.DatabaseManager;
import persistence.dao.AmministratoreDao;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//disconnessione
		if((request.getParameter("logout") != null) && (request.getParameter("logout").equals("true"))) {
			session.setAttribute("username", null);
			request.setAttribute("loggato", false);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
		session.setAttribute("username", null);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AmministratoreDao dao = DatabaseManager.getInstance().getDaoFactory().getAmministratoreDao();
		Amministratore amministratore = dao.findByPrimaryKey(username);
		
		if(amministratore == null) {

			session.setAttribute("popUp", true);
			request.setAttribute("popUpMessage", "Nessun utente registrato come " + username);	
//			response.sendRedirect("home");
		} else { 

			//connessione
			if(password.equals(amministratore.getPassword())) {
				
				session.setAttribute("username", username);				
				request.setAttribute("loggato", true);
				request.setAttribute("username", username);//JSTL
								
			} else {
				session.setAttribute("popUp", true);
				request.setAttribute("popUpMessage", "Spiacente, password non corrispondente per " + username);		
//				response.sendRedirect("home");
			}				
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
}