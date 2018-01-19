package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistence.AmministratoreCredenziali;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.AmministratoreDao;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		if((req.getParameter("logout") != null) && (req.getParameter("logout").equals("true"))) {
			
			session.setAttribute("username", null);
			
			String messaggio = "Login";
			req.setAttribute("loggato", false);
			req.setAttribute("mex", messaggio);
			
			RequestDispatcher dispacher = req.getRequestDispatcher("home.jsp");
			dispacher.forward(req, resp);
			
		} else {//TODO DA GESTIRE
			
			String username = (String) session.getAttribute("username");		
			
			if(username != null) {
				
//				out.println("<html>");
//				out.println("<head><title>Login</title></head>");
//				out.println("<body>");
//				out.println("<h1>Sei già loggato come " + username + "</h1>");
//				out.println("<a href=\"checkLogin?logout=true\">Logout</a>");
//				out.println("</body>");
//				out.println("</html>");
				
			} else {
				
//				out.println("<html>");
//				out.println("<head><title>Effettual il Login</title></head>");
//				out.println("<body>");
//				out.println("<h1>Effettua il login</h1>");
//				out.println("<form method=\"post\" action=\"checkLogin\">");
//				out.println("Username : <input name=\"username\" type=\"text\" />");
//				out.println("Password : <input name=\"password\" type=\"password\" />");
//				out.println("<input type=\"submit\" />");
//				out.println("</form>");
//				out.println("</body>");
//				out.println("</html>");
			}
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
		AmministratoreCredenziali amministratore = null;//DA ELIMINARE
		amministratore = dao.findByPrimaryKeyCredential(username);
		
		
		if(amministratore == null) {
			
			out.println("<html>");
			out.println("<head><title>Login</title></head>");
			out.println("<body>");
			out.println("<h1>Nessun amministratore è registrato come " + username + "</h1>");
			out.println("</body>");
			out.println("</html>");	
			
		} else {
			if(password.equals(amministratore.getPassword())) {
				
				session.setAttribute("username", username);
//				out.println("<html>");
//				out.println("<head><title>Login</title></head>");
//				out.println("<body>");
//				out.println("<h1>Login effettuato con successo</h1>");
//				out.println("<h2>Bentornato!</h2>");
//				out.println("</body>");
//				out.println("</html>");	
				
				String messaggio = "Login";
				req.setAttribute("loggato", true);
				req.setAttribute("mex", messaggio);
				
				RequestDispatcher dispacher = req.getRequestDispatcher("home.jsp");
				dispacher.forward(req, resp);
				
			} else {
				out.println("<html>");
				out.println("<head><title>Login</title></head>");
				out.println("<body>");
				out.println("<h1>Spiacente, password non corrispondente per l'amministratore " + username + "</h1>");			
				out.println("</body>");
				out.println("</html>");	
			}				
		}
	}
}
