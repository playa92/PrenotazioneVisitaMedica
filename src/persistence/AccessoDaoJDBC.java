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
	public void save(Accesso accesso) {
		
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO accesso(azione, data, orario, nome_utente) values(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, accesso.getAzione());
			long millis = accesso.getData().getTime();
			statement.setDate(2, new Date(millis));
			statement.setString(3, accesso.getOrario());
			statement.setString(4, accesso.getNomeUtente());
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
		Accesso a = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM accesso";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				a = new Accesso();
				a.setAzione(result.getString("azione"));
				a.setData(result.getDate("data"));				
				a.setOrario(result.getString("orario"));
				a.setNomeUtente(result.getString("nome_utente"));
				accessi.add(a);
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
	public void delete(Accesso accesso) {
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM amministratore WHERE azione = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, accesso.getAzione());
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
