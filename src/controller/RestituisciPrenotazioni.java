package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.Prenotazione;
import persistence.DatabaseManager;
import persistence.dao.PrenotazioneDao;


@SuppressWarnings("serial")
public class RestituisciPrenotazioni extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		PrenotazioneDao dao =DatabaseManager.getInstance()
				.getDaoFactory().getPrenotazioneDao();
			List<Prenotazione> pren = dao.findAll();
			req.setAttribute("prenotazioni", pren);
			
			RequestDispatcher dispacher = 
					req.getRequestDispatcher
							("prenotazione.jsp");
			dispacher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		StringBuffer jsonReceived = new StringBuffer();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));		
//		String line = reader.readLine();
//		while (line != null){
//			jsonReceived.append(line);
//			line = reader.readLine();
//		}		
//		System.out.println(jsonReceived.toString());
//		try {
//			JSONObject json = new JSONObject(jsonReceived.toString());				
//			Studente studente = new Studente();
//			studente.setMatricola(json.getString("matricola"));
//			studente.setCognome(json.getString("cognome"));			
//			studente.setNome(json.getString("nome"));
//		
//			StudenteDao dao =DatabaseManager.getInstance()
//					.getDaoFactory().getStudenteDAO();
//			Studente dbStudente  = dao.findByPrimaryKey(studente.getMatricola());
//			
//			JSONObject jsonIndirizzo = new JSONObject(dbStudente.getIndirizzo());
//			
//			PrintWriter out = resp.getWriter();
//			out.println(jsonIndirizzo.toString());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
