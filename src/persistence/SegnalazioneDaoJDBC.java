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
	public int assignId() {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select COUNT(*) AS count FROM segnalazione";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				return result.getInt(1);
			}
			
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			try {
				connection.close();
			} catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return 0;
	}
	
	@Override
	public void save(Segnalazione segnalazione) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert INTO segnalazione(id, nome_utente, cognome_utente, motivazione, commento, risposta, risolto) values(?,?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, segnalazione.getId());
			statement.setString(2, segnalazione.getNomeUtente());
			statement.setString(3, segnalazione.getCognomeUtente());
			statement.setString(4, segnalazione.getMotivazione());
			statement.setString(5, segnalazione.getCommento());
			statement.setString(6, "Nessuna");
			statement.setBoolean(7, false);
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
				segnalazione.setId(result.getInt("id"));
				segnalazione.setNomeUtente(result.getString("nome_utente"));
				segnalazione.setCognomeUtente(result.getString("cognome_utente"));
				segnalazione.setMotivazione(result.getString("motivazione"));
				segnalazione.setCommento(result.getString("commento"));
				segnalazione.setRisposta(result.getString("risposta"));
				segnalazione.setRisolto(result.getBoolean("risolto"));
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

	@Override
	public void update(Segnalazione segnalazione) {
	
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update segnalazione SET risposta = ?, risolto = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, segnalazione.getRisposta());
			statement.setBoolean(2, segnalazione.getRisolto());
			statement.setInt(3, segnalazione.getId());
			statement.executeUpdate();
			
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			try {
				connection.close();
			} catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Segnalazione segnalazione) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM segnalazione WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, segnalazione.getId());
			statement.executeUpdate();
			
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
			
		} finally {
			
			try {
				connection.close();
			} catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
}
