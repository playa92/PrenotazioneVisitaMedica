package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Amministratore;
import persistence.DatabaseManager;
import persistence.dao.AmministratoreDao;

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
		
		AmministratoreDao amministratoreDao = DatabaseManager.getInstance().
				getDaoFactory().getAmministratoreDao();
		
		Amministratore amministratore = amministratoreDao.findByPrimaryKey(username);
		
		if(amministratore != null) {
			
			amministratore.setPassword(newPassword);
			amministratoreDao.update(amministratore);
			request.setAttribute("message", username + " ha ripristinato correttamente la sua password");
			
		} else {
			request.setAttribute("message", "amministratore con user: " + username + " non presente");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/ripristino_password.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("html/ripristino_password.jsp");
	}
}
