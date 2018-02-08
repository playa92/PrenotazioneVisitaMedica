package controller;

import java.io.IOException;
import java.util.List;

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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String motivazione = request.getParameter("motivazione");
		String commento = request.getParameter("commento");
						
		SegnalazioneDao segnalazioneDao = DatabaseManager.getInstance().
				getDaoFactory().getSegnalazioneDao();
	
		Segnalazione segnalazione = new Segnalazione();
		segnalazione.setId(segnalazioneDao.assignId() + 1);
		segnalazione.setNomeUtente(nome);
		segnalazione.setCognomeUtente(cognome);
		segnalazione.setMotivazione(motivazione);
		segnalazione.setCommento(commento);
		
		segnalazioneDao.save(segnalazione);
		
		List<Segnalazione> segnalazioni = segnalazioneDao.findAll();
		request.setAttribute("segnalazioni", segnalazioni);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/restituisciSegnalazioni");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
