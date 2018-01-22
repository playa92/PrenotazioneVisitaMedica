package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Home extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			req.setAttribute("loggato", false);
		} else {
			req.setAttribute("loggato", true);
		}
		
		RequestDispatcher dispacher = req.getRequestDispatcher("home.jsp");
		dispacher.forward(req, resp);
	}
}
