package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Accesso;
import persistence.DatabaseManager;
import persistence.dao.AccessoDao;

@SuppressWarnings("serial")
public class EliminaAccesso extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccessoDao accessoDao = DatabaseManager.getInstance().getDaoFactory().getAccessoDao();
	    List<Accesso> accessi = accessoDao.findAll();
	    //TODO
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("html/visualizza_accessi.jsp");
	    dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
