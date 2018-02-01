package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));		
		String line = reader.readLine();
		
		while(line != null) {
			jsonReceived.append(line);
			line = reader.readLine();
		}		
//		System.out.println(jsonReceived.toString());//lo studente ricevuto tramite chiamata AJAX
		try {
			
			JSONObject json = new JSONObject(jsonReceived.toString());
			UniversitaDao universitaDao = DatabaseManager.getInstance().
					getDaoFactory().getUniversitaDao();
			PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance().
					getDaoFactory().getPrenotazioneDao();
			PazienteDao pazienteDao = DatabaseManager.getInstance().
					getDaoFactory().getPazienteDao();
			CodiceQRDao codiceQRDao = DatabaseManager.getInstance().
					getDaoFactory().getCodiceQRDao();
			
			Paziente paziente = new Paziente();
			paziente.setCodiceFiscale(json.getString("codiceFiscale"));
			paziente.setNome(json.getString("nome"));
			paziente.setCognome(json.getString("cognome"));
			
			if(!json.getString("matricola").equals("")) {
				paziente.setMatricola(Long.parseLong(json.getString("matricola")));
			} else {
				paziente.setMatricola(null);
			}	
			paziente.setInvalidita(json.getString("invalidita"));
		
			int t = prenotazioneDao.getTotalVisits();
			
			Date date1 = new Date(Calendar.getInstance().getTimeInMillis() + (t * TEMPO_VISITA * 60000));
			Date date2 = new Date(Calendar.getInstance().getTimeInMillis() + (t * TEMPO_VISITA * 60000) - (CONVALIDA * 60000));
			
			int indexOf = date1.toString().indexOf(":") - 2;
			String visita = date1.toString().substring(indexOf, indexOf + 8);
			String scadenza = date2.toString().substring(indexOf, indexOf + 8);
			
			Double imp = new Double(0);
			
			if(paziente.getMatricola() != null ) {
				if(universitaDao.findByPrimaryKey(paziente.getMatricola()) == null) { 
					if(paziente.getInvalidita().equals("Nessuna"))
						imp = new Double(25);
				}	
			}
			
			CodiceQR codiceQR = new CodiceQR();
			codiceQR.setCodice(json.getString("hexcode"));
			codiceQR.setScadenza(scadenza);
			codiceQR.setValido(true);
			paziente.setCodiceQR(codiceQR);
			
			Prenotazione prenotazione = new Prenotazione();
			prenotazione.setCodiceVisita(codiceQR.getCodice());
			prenotazione.setNomePaziente(paziente.getNome());
			prenotazione.setCognomePaziente(paziente.getCognome());
			prenotazione.setOrarioVisita(visita);
			prenotazione.setImporto(imp);
			
			codiceQRDao.save(codiceQR);
			pazienteDao.save(paziente);
			prenotazioneDao.save(prenotazione); 
				
			//TODO VISUALIZZARE LA PAGINA IN UN DIALOG
			PrintWriter out = response.getWriter(); 
			
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
			out.println("<h3>Codice Fiscale: " + paziente.getCodiceFiscale() + "</h3>");
			out.println("<h3>Nome: " + paziente.getNome() + "</h3>");
			out.println("<h3>Cognome: " + paziente.getCognome() + "</h3>");
			out.println("<h3>Matricola: " + String.valueOf(paziente.getMatricola()) + "</h3>");
			out.println("<h3>Invalidità: " + paziente.getInvalidita() + "</h3>");
			out.println("<h3>Importo: " + String.valueOf(prenotazione.getImporto()) + ".00 &euro;</h3>");
			out.println("<input id='text' type='hidden' value=" + codiceQR.getCodice() + "/>");
			out.println("<div id='print'>");
			out.println("<div id='qrcode'></div>");
			out.println("<h3>Codice: " + codiceQR.getCodice() + "</h3>");
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
			
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
}
