package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class EffettuaPrenotazione extends HttpServlet {
	
	private final int MAX = 50;
	private final int CONVALIDA = 20;
	private final int TEMPO_VISITA = 10;
	private final String ORARIO_INIZIO = "9:00:00"; 
	private final String ORARIO_FINE = "17:45:00";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dateFormat = "HH:mm";
		String currentTime = new SimpleDateFormat(dateFormat).format(new Date());
		
		Calendar cStart = setTimeToCalendar(dateFormat, ORARIO_INIZIO, false);
		Calendar cEnd = setTimeToCalendar(dateFormat, ORARIO_FINE, true);
		Calendar cNow = setTimeToCalendar(dateFormat, currentTime, true);
		
		Date curDate = cNow.getTime();
		if(!(curDate.after(cStart.getTime()) && curDate.before(cEnd.getTime()))) {
		    response.getWriter().write("false;Orario non valido per effettuare una prenotazione!");
			return;
		}
	
		PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance().getDaoFactory().getPrenotazioneDao();
		
		PrintWriter out = response.getWriter();
		int visiteTotali = prenotazioneDao.getTotalVisits();
		
		if(visiteTotali >= MAX) {
			out.println("redirect;Attenzione: Limite Prenotazioni raggiunto");
			return;
		}
		
		cNow.set(Calendar.MINUTE, cNow.get(Calendar.MINUTE) + 30);
		Date date = new Date(cNow.getTimeInMillis() + (visiteTotali * TEMPO_VISITA * 60000));
		String probabileVisita = new SimpleDateFormat(dateFormat).format(date);
		
		out.println("true;" + (visiteTotali + 1) + ";" + probabileVisita);
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
			
			PazienteDao pazienteDao = DatabaseManager.getInstance().getDaoFactory().getPazienteDao();
			PrenotazioneDao prenotazioneDao = DatabaseManager.getInstance().getDaoFactory().getPrenotazioneDao();
			CodiceQRDao codiceQRDao = DatabaseManager.getInstance().getDaoFactory().getCodiceQRDao();
			UniversitaDao universitaDao = DatabaseManager.getInstance().getDaoFactory().getUniversitaDao();
			
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject(jsonReceived.toString());
			
			Long matricola = null;
			
			if(!json.getString("matricola").equals("")) {
				matricola = (Long.parseLong(json.getString("matricola")));
			}
			
			if(pazienteDao.findByPrimaryKey(json.getString("codiceFiscale")) != null) {
				out.println("false;Paziente con codice fiscale '" + json.getString("codiceFiscale") + "' gia' presente");
				return;
				
			} else {
				if(pazienteDao.exists(matricola)) {
					out.println("false;La Matricola '" + json.getString("matricola") + "' e' stata associata ad un altro Paziente");
					return;
				}
			}
			
			Paziente paziente = new Paziente();
			paziente.setCodiceFiscale(json.getString("codiceFiscale"));
			paziente.setNome(json.getString("nome"));
			paziente.setCognome(json.getString("cognome"));
			paziente.setInvalidita(json.getString("invalidita"));
			
			Double imp = new Double(25);
			
			if(matricola != null) {
				
				Paziente p = universitaDao.findByPrimaryKey(matricola);
				if(p != null) {
					if(paziente.getNome().equals(p.getNome()) && paziente.getCognome().equals(p.getCognome())) {
						imp = new Double(0);
						paziente.setMatricola(matricola);
					} else {
						paziente.setMatricola(null);
					}
					
				} else {
					paziente.setMatricola(null);
				}
			}
			
			if(!paziente.getInvalidita().equals("Nessuna")) {
				imp = new Double(0);
			}
			
			int visiteTotali = prenotazioneDao.getTotalVisits();
			Calendar now = Calendar.getInstance();
			now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + 30);
			
			Date date1 = new Date(now.getTimeInMillis() + (visiteTotali * TEMPO_VISITA * 60000));
			Date date2 = new Date(now.getTimeInMillis() + (visiteTotali * TEMPO_VISITA * 60000) - (CONVALIDA * 60000));
			
			String dateFormat = "HH:mm";
			String visita = new SimpleDateFormat(dateFormat).format(date1);
			String scadenza = new SimpleDateFormat(dateFormat).format(date2);
			
			CodiceQR codiceQR = new CodiceQR();
			codiceQR.setCodice(json.getString("hexcode"));
			codiceQR.setScadenza(scadenza);
			codiceQR.setConvalida(false);
			paziente.setCodiceQR(codiceQR.getCodice());
			
			Prenotazione prenotazione = new Prenotazione();
			prenotazione.setCodiceVisita(codiceQR.getCodice());
			prenotazione.setNomePaziente(paziente.getNome());
			prenotazione.setCognomePaziente(paziente.getCognome());
			prenotazione.setOrarioVisita(visita);
			prenotazione.setImporto(imp);
			
			codiceQRDao.save(codiceQR);
			pazienteDao.save(paziente);
			prenotazioneDao.save(prenotazione); 
							
			out.println("true;" + paziente.getCodiceFiscale() + "|" + paziente.getNome() + "|" + paziente.getCognome() + "|" +
						String.valueOf(paziente.getMatricola() == null ? "Nessuna" : paziente.getMatricola()) + "|" + 
						paziente.getInvalidita() + "|" + String.valueOf(prenotazione.getImporto()) + "|" + codiceQR.getCodice());
			
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	private Calendar setTimeToCalendar(String dateFormat, String date, boolean addADay) {
	    
		Date time = null;
		try {
			time = new SimpleDateFormat(dateFormat).parse(date);
		} catch(ParseException e) {
			e.printStackTrace();
		}
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(time);

	    if(addADay) {
	        cal.add(Calendar.DATE, 1);
	    }
	    return cal;
	}
}