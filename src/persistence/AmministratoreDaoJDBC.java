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
	public void save(Amministratore amministratore) throws PersistenceException {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into amministratore(username, password) values (?,?)";
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
	public Amministratore findByPrimaryKey(String username) throws PersistenceException {
		
		System.out.println("data: "+ this.dataSource.toString());
		Connection connection = this.dataSource.getConnection();
		Amministratore amministratore = null;
		try {
			PreparedStatement statement;
			String query = "select * from amministratore where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				
				amministratore = new Amministratore();
				amministratore.setUsername(result.getString("username"));				
				amministratore.setPassword(result.getString("password"));
			}
			System.out.println("dopo creazione: " + amministratore.getPassword() + " " + amministratore.getUsername());
			
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
	public AmministratoreCredenziali findByPrimaryKeyCredential(String username) throws PersistenceException {
		
		Amministratore admin = findByPrimaryKey(username);
		AmministratoreCredenziali adminCred = null;
		if(admin != null) {
			
			adminCred = new AmministratoreCredenziali(dataSource);
			adminCred.setUsername(admin.getUsername());
			adminCred.setPassword(admin.getPassword());
		}
		return adminCred;
	}

}
