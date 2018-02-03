package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Segnalazione;
import persistence.DatabaseManager;
import persistence.dao.SegnalazioneDao;

@SuppressWarnings("serial")
public class RegistraSegnalazione extends HttpServlet {

	private static final Map<String, Integer> codici = createMap(); 
	
	private static Map<String, Integer> createMap() {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("Prenotazione non trovata", 200);
		map.put("Assenza Connessione", 404);
		map.put("Errore prenotazione", 500);
		map.put("Connessione scaduta", 504);
		return map;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String motivazione = request.getParameter("motivazione");
		
		SegnalazioneDao segnalazioneDao = DatabaseManager.getInstance().
				getDaoFactory().getSegnalazioneDao();
	
		Segnalazione segnalazione = new Segnalazione();
		segnalazione.setCodice(codici.get(motivazione));
		segnalazione.setNomeUtente(nome);
		segnalazione.setCognomeUtente(cognome);
		segnalazione.setMotivazione(motivazione);
		
		segnalazioneDao.save(segnalazione);
		
		List<Segnalazione> segnalazioni = segnalazioneDao.findAll();
		request.setAttribute("segnalazioni", segnalazioni);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/segnalazioni.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
