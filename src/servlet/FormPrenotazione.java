package servlet;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CodiceQR;
import model.Paziente;
import persistence.DatabaseManager;
import persistence.dao.PazienteDao;
import persistence.dao.UniversitaDao;
import persistence.dao.VisitaMedicaDao;

@SuppressWarnings("serial")
public class FormPrenotazione extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
		String codiceFiscale = req.getParameter("codice fiscale");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String matricola = req.getParameter("matricola");
		String invalidita = req.getParameter("invalidità");
		String hexcode = req.getParameter("hexCode");
		
//		StringBuffer jsonReceived = new StringBuffer();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));		
//		String line = reader.readLine();
//		while (line != null){
//			jsonReceived.append(line);
//			line = reader.readLine();
//		}
//		System.out.println(jsonReceived.toString());
		
		PrintWriter out = resp.getWriter();
		UniversitaDao universitaDao = DatabaseManager.getInstance().
					getDaoFactory().getUniversitaDao();
		VisitaMedicaDao visitaMedicaDao = DatabaseManager.getInstance().
				getDaoFactory().getVisitaMedicaDao();
		PazienteDao pazienteDao = DatabaseManager.getInstance().getDaoFactory().getPazienteDao();
		
		Paziente p = new Paziente();
		p.setCodiceFiscale(codiceFiscale);
		p.setNome(nome);
		p.setCognome(cognome);
		p.setMatricola(Long.parseLong(matricola));
		p.setInvalidita(invalidita);
		p.setCodice(new CodiceQR()); //gestire la creazione del codice QR
		
		if(universitaDao.findByPrimaryKey(Long.parseLong(matricola)) != null ||
				invalidita != null) {
			p.setImporto(new Double(0.0));
		} else {
			p.setImporto(new Double(25.00));
		}
		universitaDao.save(p);
		visitaMedicaDao.save(p);
		pazienteDao.save(p);
		
		out.println("<html>");
		out.println("<head><title>Riepilogo Dati</title></head>");
		out.println("<body>");
		out.println("<h1>Abbiamo registrato la prenotazione di:</h1>");
		out.println("<h4>nome: " + nome + "</h4>");
		out.println("<h4>cognome: " + cognome + "</h4>");
		out.println("<h4>cf: " + codiceFiscale + " </h4>");
		out.println("<h4>matricola: " + matricola + "</h4>");
		out.println("<h4>invalidità: " + invalidita + "</h4>");
		out.println("<h4>importo: " + String.valueOf(p.getImporto()) + " &euro;</h4>");
		out.println("<h4>esadecimale: " + hexcode + "</h4>");
		out.println("</body>");
		out.println("</html>");
		
		req.setAttribute("submit", true);
		
//		RequestDispatcher dispacher = 
//				req.getRequestDispatcher("prenotazione.jsp");
//		dispacher.forward(req, resp);
	}
}
