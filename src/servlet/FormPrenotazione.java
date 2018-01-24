package servlet;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CodiceQR;
import model.Paziente;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
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
		PazienteDao pazienteDao = DatabaseManager.getInstance().
				getDaoFactory().getPazienteDao();
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
				getDaoFactory().getCodiceQRDao();
		
		Date date = Date.from(Instant.now());
		
		CodiceQR c = new CodiceQR();
		c.setCodice(hexcode);
		c.setScadenza(date);
		c.setValido(true);
		
		Paziente p = new Paziente();
		p.setCodiceFiscale(codiceFiscale);
		p.setNome(nome);
		p.setCognome(cognome);
		p.setMatricola(Long.parseLong(matricola));
		p.setInvalidita(invalidita);
		p.setCodice(c);
		
//		System.out.println(universitaDao.findByPrimaryKey(Long.parseLong(matricola)).toString());
		
		if(matricola != null && 
				(universitaDao.findByPrimaryKey(Long.parseLong(matricola)) != null || invalidita != null)) {
				p.setImporto(new Double(0.0));
		} else {
			p.setImporto(new Double(25.00));
		}
		
		codiceQRDao.save(c);
		pazienteDao.save(p);
		visitaMedicaDao.save(p);
		
		out.println("<html>");
		out.println("<div id=\"content\">");
		out.println("<head><title>Riepilogo Dati</title></head>");
		out.println("<body>");
		out.println("<h1>Abbiamo registrato la prenotazione di:</h1>");
		out.println("<h3>nome: " + nome + "</h3>");
		out.println("<h3>cognome: " + cognome + "</h3>");
		out.println("<h3>cf: " + codiceFiscale + " </h3>");
		out.println("<h3>matricola: " + matricola + "</h3>");
		out.println("<h3>invalidità: " + invalidita + "</h3>");
		out.println("<h3>importo: " + String.valueOf(p.getImporto()) + " &euro;</h3>");
		final String link = "http://api.qrserver.com/v1/create-qr-code/?data=" + hexcode;
		out.println("<img width=\"100\" height=\"100\" src=" + link + " alt=\"\">");
		out.println("<h3>esadecimale: " + hexcode + "</h3>");
		out.println("</div>");
		out.println("<div id='editor'></div>");
		out.println("<button id='cmd'>generate PDF</button>");
		
//		out.println("<script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");
//		out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.2.61/jspdf.min.js'></script>");
//		out.println("script src='../WebContent/js/pdf-save.js'></script>");
		
		out.println("</body>");
		out.println("</html>");
		
		req.setAttribute("submit", true);
		
//		RequestDispatcher dispacher = 
//				req.getRequestDispatcher("prenotazione.jsp");
//		dispacher.forward(req, resp);
	}
}
