package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
//import model.CodiceQR;
import model.Paziente;
import persistence.dao.UniversitaDao;

public class UniversitaDaoJDBC implements UniversitaDao {

	private DataSource dataSource;
	
	public UniversitaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Paziente paziente) {
		
		Connection connection = this.dataSource.getConnection();
		try {
		String query = "insert INTO università(matricola, nome_paziente, cognome_paziente) values(?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, paziente.getMatricola());
		statement.setString(2, paziente.getNome());
		statement.setString(3, paziente.getCognome());
		statement.executeUpdate();
		
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Paziente findByPrimaryKey(Long id) {
		
		Connection connection = this.dataSource.getConnection();
		Paziente paziente = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM università WHERE matricola = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				paziente = new Paziente();	
				paziente.setCodiceFiscale(result.getString("matricola"));
				paziente.setNome(result.getString("nome"));				
				paziente.setCognome(result.getString("cognome"));
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
		return paziente;
	}

	@Override
	public List<Paziente> findAll() {
		
		Connection connection = this.dataSource.getConnection();
		List<Paziente> universitari = new LinkedList<>();
		Paziente paziente = null;
		try {
			String query = "select * FROM univeristà";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				paziente = new Paziente();
				paziente.setCodiceFiscale(result.getString("matricola"));
				paziente.setNome(result.getString("nome"));
				paziente.setCognome(result.getString("cognome"));
				universitari.add(paziente);
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
		return universitari;
	}
}
