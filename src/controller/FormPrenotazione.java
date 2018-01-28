package controller;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
		String codiceFiscale = req.getParameter("codice fiscale");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String matricola = req.getParameter("matricola");
		String invalidita = req.getParameter("invalidita");
		String hexcode = req.getParameter("hexCode");
		
		if(matricola.equals("")) {
			matricola = "N/A";
		}
		
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
		
		int t = visitaMedicaDao.getTotalVisits();
//		System.out.println("total: " + t);
		
		//DATA SCADENZA = TEMPO CORRENTE + (totale visite * 15 (tempo per ogni visita)) 
		Date date = new Date(Calendar.getInstance().getTimeInMillis() + (t * 15) * 60000);
//		System.out.println("date: " + date.toString());
		
		CodiceQR c = new CodiceQR();
		c.setCodice(hexcode);
		c.setScadenza(date);
		c.setValido(true);
		
		Paziente p = new Paziente();
		p.setCodiceFiscale(codiceFiscale);
		p.setNome(nome);
		p.setCognome(cognome);
		if(!matricola.equals("N/A"))
			p.setMatricola(Long.parseLong(matricola));
		else 
			p.setMatricola(null);
		p.setInvalidita(invalidita);
		p.setCodice(c);
		
		if(!matricola.equals("N/A")) { 
			if(universitaDao.findByPrimaryKey(Long.parseLong(matricola)) != null
					|| !invalidita.equals("Nessuna"))
				p.setImporto(new Double(0));
			else
				p.setImporto(new Double(25));
		} else {
			if(!invalidita.equals("Nessuna"))
				p.setImporto(new Double(0));
			else
				p.setImporto(new Double(25));
		}
		
		//formattazione dell'importo
        DecimalFormat format = new DecimalFormat("0.00");
        String formattedImp = format.format(p.getImporto());
		 
		codiceQRDao.save(c);
		pazienteDao.save(p);
		visitaMedicaDao.save(p);

		out.println("<html>");
		out.println("<head><title>Riepilogo Dati</title>");
		out.println("<link rel='stylesheet' href='bootstrap-3.3.7-dist/css/bootstrap.min.css'>");
		out.println("<script src='js/jquery/jquery-3.2.1.min.js'></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js\"></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"js/jquery/jquery.qrcode.min.js\"></script>");	
		out.println("<script src='js/pdf_print.js'></script>");
		out.println("<script src='js/jquery/jquery.qrcode.js'></script>");
		out.println("</head>");
		out.println("<body>");
//		out.println("<div id='content' style='background-color: white;'>");
		out.println("<h1>Abbiamo registrato la tua prenotazione:</h1>");
		out.println("<h3>Codice Fiscale: " + codiceFiscale + " </h3>");
		out.println("<h3>Nome: " + nome + "</h3>");
		out.println("<h3>Cognome: " + cognome + "</h3>");
		out.println("<h3>Matricola: " + matricola + "</h3>");
		out.println("<h3>Invalidità: " + invalidita + "</h3>");
		out.println("<h3>Importo: " + formattedImp + " &euro;</h3>");
		out.println("<div id='content' style='background-color: white;'>");
		out.println("<input id='text' type='hidden' value=" + hexcode + "/>");
		out.println("<div id='qrcode'></div>");
		out.println("<script src='js/qr_code.js'></script>");
		out.println("<h3>Codice: " + hexcode + "</h3>");
		out.println("</div>");
		out.println("<h3 style='color:red'>stampa promemoria "
				+ "<button id='cmd' type='button' class='btn btn-default btn-sm'>"
				+ "<span class='glyphicon glyphicon-print'></span> PDF</button></h3>");
		out.println("</body>");
		out.println("</html>");
	}
}
