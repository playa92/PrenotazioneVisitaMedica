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
	public void save(Prenotazione visitaMedica) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "insert INTO prenotazione(id_visita, nome_paziente, cognome_paziente, orario_visita, importo) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, visitaMedica.getCodiceVisita());
			statement.setString(2, visitaMedica.getNomePaziente());
			statement.setString(3, visitaMedica.getCognomePaziente());
			statement.setString(4, visitaMedica.getOrarioVisita());
			statement.setDouble(5, visitaMedica.getImporto());
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
		Prenotazione visitaMedica = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM prenotazione WHERE id_visita = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				visitaMedica = new Prenotazione();
				visitaMedica.setCodiceVisita(result.getString(1));
				visitaMedica.setNomePaziente(result.getString(2));
				visitaMedica.setCognomePaziente(result.getString(3));
				visitaMedica.setOrarioVisita(result.getString(4));
				visitaMedica.setImporto(result.getDouble(5));
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
		return visitaMedica;
	}

	@Override
	public List<Prenotazione> findAll() {
		
		Connection connection = this.dataSource.getConnection();
		List<Prenotazione> visite = new LinkedList<>();
		Prenotazione visitaMedica = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM prenotazione";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				visitaMedica = new Prenotazione();
				visitaMedica.setCodiceVisita(result.getString(1));
				visitaMedica.setNomePaziente(result.getString(2));
				visitaMedica.setCognomePaziente(result.getString(3));
				visitaMedica.setOrarioVisita(result.getString(4));
				visitaMedica.setImporto(result.getDouble(5));
				visite.add(visitaMedica);
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
	public void update(Prenotazione visitaMedica) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update prenotazione SET id_visita = ?, nome_paziente = ?, cognome_paziente = ?, orario_visita = ?, importo = ?, WHERE id_visita = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, visitaMedica.getCodiceVisita());
			statement.setString(2,visitaMedica.getNomePaziente());
			statement.setString(3, visitaMedica.getCognomePaziente());
			statement.setString(4, visitaMedica.getOrarioVisita());
			statement.setDouble(5, visitaMedica.getImporto());
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
	public void delete(Prenotazione visitaMedica) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM prenotazione WHERE id_visita = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, visitaMedica.getCodiceVisita());
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
		
		int total = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select COUNT(*) AS total FROM prenotazione";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				total = result.getInt(1);
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
		return total;
	}

}
