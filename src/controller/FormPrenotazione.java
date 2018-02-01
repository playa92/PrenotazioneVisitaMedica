package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.CodiceQR;
import model.Paziente;
import model.Prenotazione;
import persistence.DatabaseManager;
import persistence.dao.CodiceQRDao;
import persistence.dao.PazienteDao;
import persistence.dao.UniversitaDao;
import persistence.dao.PrenotazioneDao;

@SuppressWarnings("serial")
public class FormPrenotazione extends HttpServlet {
	
	private final int CONVALIDA = 20;
	private final int TEMPO_VISITA = 15;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		
		System.out.println("post");
		
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));		
		String line = reader.readLine();
		while (line != null){
			jsonReceived.append(line);
			line = reader.readLine();
		}		
		System.out.println(jsonReceived.toString());//lo studente ricevuto tramite chiamata AJAX
		try {
			
			JSONObject json = new JSONObject(jsonReceived.toString());				
			Paziente p = new Paziente();
			p.setCodiceFiscale(json.getString("codiceFiscale"));
			p.setNome(json.getString("nome"));
			p.setCognome(json.getString("cognome"));
			if(!json.getString("matricola").equals("")) {
				p.setMatricola(Long.parseLong(json.getString("matricola")));
			} else {
				p.setMatricola(null);
			}
			p.setInvalidita(json.getString("invalidita"));
			String hex = json.getString("esadecimale");
			
			System.out.println("-------> " + hex);
			
			PazienteDao dao =DatabaseManager.getInstance()
					.getDaoFactory().getPazienteDao();
			
			Paziente dbStudente  = dao.findByPrimaryKey(p.getCodiceFiscale());
			
			if(dbStudente != null) {
				
				dao.save(p);
				PrintWriter out = response.getWriter();
				out.println(json.toString());
				return;
			} else {
				response.getWriter().write("nessun studente trovato");
				return;
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		String codiceFiscale = request.getParameter("codice fiscale");
//		String nome = request.getParameter("nome");
//		String cognome = request.getParameter("cognome");
//		String matricola = request.getParameter("matricola");
//		String invalidita = request.getParameter("invalidita");
//		String hexcode = request.getParameter("hexCode");
//		
//		if(matricola.equals("")) {
//			matricola = "N/A";
//		}
//		
//		PrintWriter out = response.getWriter();
//		UniversitaDao universitaDao = DatabaseManager.getInstance().
//				getDaoFactory().getUniversitaDao();
//		PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance().
//				getDaoFactory().getPrenotazioneDao();
//		PazienteDao pazienteDao = DatabaseManager.getInstance().
//				getDaoFactory().getPazienteDao();
//		CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
//				getDaoFactory().getCodiceQRDao();
//		
//		int t = prenotazioneDao.getTotalVisits();
//
//		//DATA SCADENZA = TEMPO CORRENTE + (totale visite * 15 (tempo per ogni visita)) 
//		Date date = new Date(Calendar.getInstance().getTimeInMillis() + (t * TEMPO_VISITA * 60000));
//		Date date2 = new Date(Calendar.getInstance().getTimeInMillis() + (t * TEMPO_VISITA * 60000) - (CONVALIDA * 60000));
//		int indexOf = date.toString().indexOf(":") - 2;
//		String visita = date.toString().substring(indexOf, indexOf + 8); //considero il tempo nel formato hh:mm:ss
//		String convalida = date2.toString().substring(indexOf, indexOf + 8);
//		
//		CodiceQR codiceQR = new CodiceQR();
//		codiceQR.setCodice(hexcode);
////		c.setScadenza(date);
//		codiceQR.setScadenza(convalida); //TEMPORANEO
//		codiceQR.setValido(true);
//		
//		Paziente paziente = new Paziente();
//		paziente.setCodiceFiscale(codiceFiscale);
//		paziente.setNome(nome);
//		paziente.setCognome(cognome);
//		if(!matricola.equals("N/A"))
//			paziente.setMatricola(Long.parseLong(matricola));
//		else 
//			paziente.setMatricola(null);
//		paziente.setInvalidita(invalidita);
//		paziente.setCodiceQR(codiceQR);
//		
//		Double imp = new Double(0);
//		
//		if(!matricola.equals("N/A")) { 
//			if(universitaDao.findByPrimaryKey(Long.parseLong(matricola)) != null
//					|| !invalidita.equals("Nessuna"))
//				imp = new Double(25);
//		} else {
//			if(!invalidita.equals("Nessuna"))
//				imp = new Double(25);
//		}
//		
//		Prenotazione prenotazione = new Prenotazione();
//		prenotazione.setCodiceVisita(codiceQR.getCodice());
//		prenotazione.setNomePaziente(paziente.getNome());
//		prenotazione.setCognomePaziente(paziente.getCognome());
//		prenotazione.setOrarioVisita(visita);
//		prenotazione.setImporto(imp);
//		
//		//formattazione dell'importo
//        DecimalFormat format = new DecimalFormat("0.00");
//        String formattedImp = format.format(prenotazione.getImporto());
//		 
//		codiceQRDao.save(codiceQR);
//		pazienteDao.save(paziente);
//		prenotazioneDao.save(prenotazione);  
//	
//		out.println("<html>");
//		out.println("<head><title>Riepilogo Dati</title>");
//		out.println("<link rel='stylesheet' href='bootstrap-3.3.7-dist/css/bootstrap.min.css'>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<div style='position:relative;float:right;'>");
//		out.println("<button type='button'class='btn btn-default btn-md' onclick=\"window.location='home.jsp'\">");
//		out.println("<span class='glyphicon glyphicon-home'> Home</span>");
//		out.println("</button>");
//		out.println("</div>");
//		out.println("<div id='content style='background-color:white;'>");
//		out.println("<h1>Abbiamo registrato la tua prenotazione:</h1>");
//		out.println("<h3>Codice Fiscale: " + codiceFiscale + "</h3>");
//		out.println("<h3>Nome: " + nome + "</h3>");
//		out.println("<h3>Cognome: " + cognome + "</h3>");
//		out.println("<h3>Matricola: " + matricola + "</h3>");
//		out.println("<h3>Invalidità: " + invalidita + "</h3>");
//		out.println("<h3>Importo: " + formattedImp + " &euro;</h3>");
//		out.println("<input id='text' type='hidden' value=" + hexcode + "/>");
//		out.println("<div id='print'>");
//		out.println("<div id='qrcode'></div>");
//		out.println("<h3>Codice: " + hexcode + "</h3>");
//		out.println("</div>");
//		out.println("</div>"); 
//		out.println("<h3 style='color:red'>stampa promemoria "
//				+ "<button id='cmd' type='button' class='btn btn-default btn-sm'>"
//				+ "<span class='glyphicon glyphicon-print'></span> PDF</button></h3>");
//		out.println("<script src='js/jquery/jquery-3.2.1.min.js'></script>");
//		out.println("<script src='js/jquery/jquery.qrcode.js'></script>");
//		out.println("<script src='js/jquery/html2canvas.js'></script>");
//		out.println("<script src='js/jquery/jspdf.min.js'></script>");
//		out.println("<script src='js/qr_code.js'></script>");
//		out.println("<script src='js/pdf_print.js'></script>");
//		out.println("</body>");
//		out.println("</html>");
	}
}
