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
	
	private final int CONVALIDA = 20;
	private final int TEMPO_VISITA = 15;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		
		String codiceFiscale = request.getParameter("codice fiscale");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String matricola = request.getParameter("matricola");
		String invalidita = request.getParameter("invalidita");
		String hexcode = request.getParameter("hexCode");
		
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
		
		PrintWriter out = response.getWriter();
		UniversitaDao universitaDao = DatabaseManager.getInstance().
				getDaoFactory().getUniversitaDao();
		VisitaMedicaDao visitaMedicaDao = DatabaseManager.getInstance().
				getDaoFactory().getVisitaMedicaDao();
		PazienteDao pazienteDao = DatabaseManager.getInstance().
				getDaoFactory().getPazienteDao();
		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
				getDaoFactory().getCodiceQRDao();
		
		int t = visitaMedicaDao.getTotalVisits();

		//DATA SCADENZA = TEMPO CORRENTE + (totale visite * 15 (tempo per ogni visita)) 
		Date date = new Date(Calendar.getInstance().getTimeInMillis() + (t * TEMPO_VISITA * 60000));
		Date date2 = new Date(Calendar.getInstance().getTimeInMillis() + (t * TEMPO_VISITA * 60000) - (CONVALIDA * 60000));
		int indexOf = date.toString().indexOf(":") - 2;
		String visita = date.toString().substring(indexOf, indexOf + 8); //considero il tempo nel formato hh:mm:ss
		String convalida = date2.toString().substring(indexOf, indexOf + 8);
		
		CodiceQR c = new CodiceQR();
		c.setCodice(hexcode);
//		c.setScadenza(date);
		c.setScadenza(visita + ";" + convalida); //TEMPORANEO
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
		p.setCodiceQR(c);
		
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
		out.println("</head>");
		out.println("<body>");
		out.println("<div style='position:relative;float:right;'>");
		out.println("<button type='button'class='btn btn-default btn-md' onclick=\"window.location='home.jsp'\">");
		out.println("<span class='glyphicon glyphicon-home'> Home</span>");
		out.println("</button>");
		out.println("</div>");
		out.println("<div id='content style='background-color:white;'>");
		out.println("<h1>Abbiamo registrato la tua prenotazione:</h1>");
		out.println("<h3>Codice Fiscale: " + codiceFiscale + "</h3>");
		out.println("<h3>Nome: " + nome + "</h3>");
		out.println("<h3>Cognome: " + cognome + "</h3>");
		out.println("<h3>Matricola: " + matricola + "</h3>");
		out.println("<h3>Invalidità: " + invalidita + "</h3>");
		out.println("<h3>Importo: " + formattedImp + " &euro;</h3>");
		out.println("<input id='text' type='hidden' value=" + hexcode + "/>");
		out.println("<div id='print'>");
		out.println("<div id='qrcode'></div>");
		out.println("<h3>Codice: " + hexcode + "</h3>");
		out.println("</div>");
		out.println("</div>"); 
		out.println("<h3 style='color:red'>stampa promemoria "
				+ "<button id='cmd' type='button' class='btn btn-default btn-sm'>"
				+ "<span class='glyphicon glyphicon-print'></span> PDF</button></h3>");
		out.println("<script src='js/jquery/jquery-3.2.1.min.js'></script>");
		out.println("<script src='js/jquery/jquery.qrcode.js'></script>");
		out.println("<script src='js/jquery/html2canvas.js'></script>");
		out.println("<script src='js/jquery/jspdf.min.js'></script>");
		out.println("<script src='js/qr_code.js'></script>");
		out.println("<script src='js/pdf_print.js'></script>");
		out.println("</body>");
		out.println("</html>");
	}
}
