package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Accesso;
import persistence.dao.AccessoDao;

public class AccessoDaoJDBC implements AccessoDao {

	private DataSource dataSource;
	
	public AccessoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int assignId() {
		
		Connection connection = dataSource.getConnection();
		try {
			String query = "select COUNT(*) AS count FROM accesso";
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
	public void save(Accesso accesso) {
		
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO accesso(id, azione, data, orario, nome_utente) values(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, accesso.getId());
			statement.setString(2, accesso.getAzione());
			long millis = accesso.getData().getTime();
			statement.setDate(3, new Date(millis));
			statement.setString(4, accesso.getOrario());
			statement.setString(5, accesso.getNomeUtente());
			statement.executeUpdate();
			
		} catch (SQLException e) {
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
	public List<Accesso> findAll() {
		
		Connection connection = dataSource.getConnection();
		List<Accesso> accessi = new ArrayList<>();
		Accesso accesso = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM accesso";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				accesso = new Accesso();
				accesso.setId(result.getInt("id"));
				accesso.setAzione(result.getString("azione"));
				accesso.setData(result.getDate("data"));				
				accesso.setOrario(result.getString("orario"));
				accesso.setNomeUtente(result.getString("nome_utente"));
				accessi.add(accesso);
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
		return accessi;
	}

	@Override
	public void deleteAll() {
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM accesso";
			PreparedStatement statement = connection.prepareStatement(delete);
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
