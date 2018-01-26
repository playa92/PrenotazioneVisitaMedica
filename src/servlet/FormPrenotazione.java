package servlet;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
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
		String invalidita = req.getParameter("invalidita");
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
		
		if(matricola != null && (universitaDao.findByPrimaryKey(Long.parseLong(matricola)) != null
				|| !invalidita.equals("Nessuna"))) {
				p.setImporto(new Double(0.0));
		} else {
			p.setImporto(new Double(25.00));
		}
		 
		codiceQRDao.save(c);
		pazienteDao.save(p);
		visitaMedicaDao.save(p);
				
		out.println("<html>");
		out.println("<head><title>Riepilogo Dati</title>");
		
		out.println("<script src='js/jquery/jquery-3.2.1.min.js'></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js\"></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"js/jquery/jquery.qrcode.min.js\"></script>");	
		out.println("<script src='js/pdf_print.js'></script>");
		out.println("<script src=\"js/qr_code.js\"></script>");
		
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"content\" style=\"background-color: white;\">");
		out.println("<h1>Abbiamo registrato la tua prenotazione:</h1>");
		out.println("<h3>Nome:" + nome + "</h3>");
		out.println("<h3>Cognome:" + cognome + "</h3>");
		out.println("<h3>C.F.:" + codiceFiscale + " </h3>");
		out.println("<h3>Matricola:" + matricola + "</h3>");
		out.println("<h3>Invalidità:" + invalidita + "</h3>");
		out.println("<h3>Importo:" + String.valueOf(p.getImporto()) + " &euro;</h3>");
		
		out.println("<p id=\"#output\"></p>");
		
		out.println("<h3>Codice:" + hexcode + "</h3>");
		out.println("</div>");
		out.println("<button id='cmd'>generate PDF</button>");
		out.println("</body>");
		out.println("</html>");
	}
}
