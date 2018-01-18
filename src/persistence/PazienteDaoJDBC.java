package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Paziente;
import persistence.dao.PazienteDao;

public class PazienteDaoJDBC implements PazienteDao {

	private DataSource dataSource;

	public PazienteDaoJDBC(DataSource dataSource) {
			this.dataSource = dataSource;
	}

	@Override
	public void save(Paziente paziente) throws PersistenceException {
			
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into paziente(nome, cognome, matricola, invalidità) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, paziente.getName());
			statement.setString(2, paziente.getSurname());
			statement.setLong(3, paziente.getId());
			statement.setString(4, paziente.getInvalidity());
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
	public Paziente findByPrimaryKey(Long id) throws PersistenceException {
		
		Connection connection = this.dataSource.getConnection();
		Paziente paziente = null;
		try {
			PreparedStatement statement;
			String query = "select * from paziente where matricola = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(3, id);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				paziente = new Paziente();
				paziente.setName(result.getString("nome"));				
				paziente.setSurname(result.getString("cognome"));
				paziente.setId(result.getLong("matricola"));
				paziente.setInvalidity(result.getString("invalidità"));
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
	public List<Paziente> findAll() throws PersistenceException {
		
		Connection connection = this.dataSource.getConnection();
		List<Paziente> pazienti = new LinkedList<>();
		Paziente paziente = null;
		try {
			PreparedStatement statement;
			String query = "select * from paziente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				paziente = new Paziente();
				paziente.setName(result.getString("nome"));				
				paziente.setSurname(result.getString("cognome"));
				paziente.setId(result.getLong("matricola"));
				paziente.setInvalidity(result.getString("invalidità"));
				pazienti.add(paziente);
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
		return pazienti;
	}

	@Override
	public void update(Paziente paziente) throws PersistenceException {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update paziente SET nome = ?, cognome = ?, matricola = ?, invalidità = ? WHERE matricola = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, paziente.getName());
			statement.setString(2, paziente.getSurname());
			statement.setLong(3, paziente.getId());
			statement.setString(4, paziente.getInvalidity());
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
	public void delete(Paziente paziente) throws PersistenceException {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM paziente WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(3, paziente.getId());
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
