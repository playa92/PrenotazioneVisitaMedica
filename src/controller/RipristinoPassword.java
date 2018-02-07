package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Impiegato;
import persistence.DatabaseManager;
import persistence.dao.ImpiegatoDao;

@SuppressWarnings("serial")
public class RipristinoPassword extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String newPassword = request.getParameter("newPassword");
		
		ImpiegatoDao impiegatoDao = DatabaseManager.getInstance().
				getDaoFactory().getImpiegatoDao();
		
		Impiegato impiegato = impiegatoDao.findByPrimaryKey(username);
			
		if(impiegato != null) {
			
			impiegato.setPassword(newPassword);
			impiegatoDao.update(impiegato);
			request.setAttribute("message", "Password ripristinata correttamente");
			
		} else {
			request.setAttribute("message", "impiegato con user '" + username + "' non presente");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/ripristino_password.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("html/ripristino_password.jsp");
	}
}
