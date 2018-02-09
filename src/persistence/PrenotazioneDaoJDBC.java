package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Prenotazione;
import persistence.dao.PrenotazioneDao;

public class PrenotazioneDaoJDBC implements PrenotazioneDao {

	private DataSource dataSource;
	
	public PrenotazioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Prenotazione prenotazione) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "insert INTO prenotazione(id_visita, nome_paziente, cognome_paziente, orario_visita, importo) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, prenotazione.getCodiceVisita());
			statement.setString(2, prenotazione.getNomePaziente());
			statement.setString(3, prenotazione.getCognomePaziente());
			statement.setString(4, prenotazione.getOrarioVisita());
			statement.setDouble(5, prenotazione.getImporto());
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
	public Prenotazione findByPrimaryKey(String codice) {
		
		Connection connection = this.dataSource.getConnection();
		Prenotazione prenotazione = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM prenotazione WHERE id_visita = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				prenotazione = new Prenotazione();
				prenotazione.setCodiceVisita(result.getString(1));
				prenotazione.setNomePaziente(result.getString(2));
				prenotazione.setCognomePaziente(result.getString(3));
				prenotazione.setOrarioVisita(result.getString(4));
				prenotazione.setImporto(result.getDouble(5));
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
		return prenotazione;
	}

	@Override
	public List<Prenotazione> findAll() {
		
		Connection connection = this.dataSource.getConnection();
		List<Prenotazione> visite = new LinkedList<>();
		Prenotazione prenotazione = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM prenotazione";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				prenotazione = new Prenotazione();
				prenotazione.setCodiceVisita(result.getString(1));
				prenotazione.setNomePaziente(result.getString(2));
				prenotazione.setCognomePaziente(result.getString(3));
				prenotazione.setOrarioVisita(result.getString(4));
				prenotazione.setImporto(result.getDouble(5));
				visite.add(prenotazione);
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
		return visite;
	}

	@Override
	public void update(Prenotazione prenotazione) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update prenotazione SET nome_paziente = ?, cognome_paziente = ?, orario_visita = ?, importo = ? WHERE id_visita = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, prenotazione.getNomePaziente());
			statement.setString(2, prenotazione.getCognomePaziente());
			statement.setString(3, prenotazione.getOrarioVisita());
			statement.setDouble(4, prenotazione.getImporto());
			statement.setString(5, prenotazione.getCodiceVisita());
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
	public void delete(Prenotazione prenotazione) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM prenotazione WHERE id_visita = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, prenotazione.getCodiceVisita());
			statement.executeUpdate();
			
			delete = "delete FROM paziente WHERE id_codice = ?";
			statement = connection.prepareStatement(delete);
			statement.setString(1, prenotazione.getCodiceVisita());
			statement.executeUpdate();
			
			delete = "delete FROM codice_qr WHERE id = ?";
			statement = connection.prepareStatement(delete);
			statement.setString(1, prenotazione.getCodiceVisita());
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
	public int getTotalVisits() {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select COUNT(*) AS total FROM prenotazione";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				return result.getInt(1);
			}
			
		} catch (SQLException e) {
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
}
