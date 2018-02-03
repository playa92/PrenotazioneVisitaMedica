package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Segnalazione;
import persistence.dao.SegnalazioneDao;

public class SegnalazioneDaoJDBC implements SegnalazioneDao {

	private DataSource dataSource;
	
	public SegnalazioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Segnalazione segnalazione) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert INTO segnalazione(codice, nome_utente, cognome_utente, motivazione) values(?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, segnalazione.getCodice());
			statement.setString(2, segnalazione.getNomeUtente());
			statement.setString(3, segnalazione.getCognomeUtente());
			statement.setString(4, segnalazione.getMotivazione());
			statement.executeUpdate();
			
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			
			try {
				connection.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Segnalazione> findAll() {
		
		Connection connection = this.dataSource.getConnection();
		List<Segnalazione> segnalazioni = new LinkedList<>();
		Segnalazione segnalazione = null;
		try {
			String query = "select * FROM segnalazione";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
			
				segnalazione = new Segnalazione();
				segnalazione.setCodice(result.getInt("codice"));
				segnalazione.setNomeUtente(result.getString("nome_utente"));
				segnalazione.setCognomeUtente(result.getString("cognome_utente"));
				segnalazione.setMotivazione(result.getString("motivazione"));
				segnalazioni.add(segnalazione);
			}
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			
			try {
				connection.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return segnalazioni;
	}
}
