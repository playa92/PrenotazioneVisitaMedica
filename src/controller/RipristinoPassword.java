package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String newPassword = request.getParameter("newPassword");
		
		ImpiegatoDao impiegatoDao = DatabaseManager.getInstance().getDaoFactory().getImpiegatoDao();
		Impiegato impiegato = impiegatoDao.findByPrimaryKey(username);
			
		if(impiegato != null) {
			
			impiegato.setPassword(newPassword);
			impiegatoDao.update(impiegato);
			session.setAttribute("message", "Password ripristinata correttamente");
			
		} else {
			session.setAttribute("message", "Impiegato con user '" + username + "' non presente");
		}
		response.sendRedirect("html/ripristino_password.jsp");
	}
}
