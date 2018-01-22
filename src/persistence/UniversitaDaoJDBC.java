package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.CodiceQR;
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
		String query = "insert INTO università(matricola_p, nome_p, cognome_p) values(?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		
		if(paziente.getMatricola() != null) { 
			
			statement.setLong(1, paziente.getMatricola());
			statement.setString(2, paziente.getNome());
			statement.setString(3, paziente.getCognome());
		}
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
			String query = "select * FROM paziente WHERE matricola = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				paziente = new Paziente();	
				paziente.setCodiceFiscale(result.getString("cf"));
				paziente.setNome(result.getString("nome"));				
				paziente.setCognome(result.getString("cognome"));
				paziente.setMatricola(result.getLong("matricola"));
				paziente.setInvalidita(result.getString("invalidità"));
				paziente.setCodice((CodiceQR) result.getObject("id_codiceQr"));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Paziente paziente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Paziente paziente) {
		// TODO Auto-generated method stub
		
	}
}
