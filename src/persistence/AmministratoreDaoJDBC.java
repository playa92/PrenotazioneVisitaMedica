package persistence;

import java.sql.*;
import model.Amministratore;
import persistence.dao.AmministratoreDao;

public class AmministratoreDaoJDBC implements AmministratoreDao {

	private DataSource dataSource;
	
	public AmministratoreDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Amministratore amministratore) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert INTO amministratore(username, password) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, amministratore.getUsername());
			statement.setString(2, amministratore.getPassword());
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
	public Amministratore findByPrimaryKey(String username) {
		
		Connection connection = this.dataSource.getConnection();
		Amministratore amministratore = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM amministratore WHERE username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				
				amministratore = new Amministratore();
				amministratore.setUsername(result.getString("username"));				
				amministratore.setPassword(result.getString("password"));
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
		return amministratore;
	}
	
	@Override
	public void delete(Amministratore amministratore) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM amministratore WHERE username = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, amministratore.getUsername());
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
