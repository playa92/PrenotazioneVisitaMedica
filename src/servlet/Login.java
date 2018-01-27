package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		//disconnessione
		if((req.getParameter("logout") != null) && (req.getParameter("logout").equals("true"))) {
			session.setAttribute("username", null);
			req.setAttribute("loggato", false);		
			RequestDispatcher dispacher = req.getRequestDispatcher("home.jsp");
			dispacher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		HttpSession session = req.getSession();
		session.setAttribute("username", null);
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		AmministratoreDao dao = DatabaseManager.getInstance().getDaoFactory().getAmministratoreDao();
		Amministratore amministratore = dao.findByPrimaryKey(username);

		System.out.println( req.getParameter("prova") );

		System.out.println( req.getParameter("prova2") );

		
		if(amministratore == null) {
			
			resp.setContentType("text/html;charset=UTF-8");
		  
		    try { 
		        out.println("Nessunn amministratore registrato come "+username);
		    } finally {
		        out.close();
		    } 
			 
		} else { 
			
			
			System.out.println("else");
			//connessione
			if(password.equals(amministratore.getPassword())) {
	
				session.setAttribute("username", username);				
				req.setAttribute("loggato", true);
				RequestDispatcher dispacher = req.getRequestDispatcher("home.jsp");
				dispacher.forward(req, resp);
				
			} else {
//				out.println("<html>");
//				out.println("<head><title>Login</title></head>");
//				out.println("<body>");
//				out.println("<h1>Spiacente, password non corrispondente per l'amministratore " + username + "</h1>");			
//				out.println("</body>");
//				out.println("</html>");	
				
				System.out.println("Spiacente, password non corrispondente per: "+username);
				req.setAttribute("corrispondente", false);
				RequestDispatcher dispacher = req.getRequestDispatcher("home.jsp");
				dispacher.forward(req, resp);
			}				
		}
	}
}