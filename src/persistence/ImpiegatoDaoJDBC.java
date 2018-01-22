package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Impiegato;
import persistence.dao.ImpiegatoDao;

public class ImpiegatoDaoJDBC implements ImpiegatoDao {

	private DataSource dataSource;
	
	public ImpiegatoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Impiegato impiegato) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert INTO impiegato(id, nome, ruolo) values(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, impiegato.getId());
			statement.setString(2, impiegato.getNome());
			statement.setString(3, impiegato.getRuolo());
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
	public void update(Impiegato impiegato) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "update FROM impiegato set id = ?, nome = ?, ruolo = ? WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, impiegato.getId());
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
	public void delete(Impiegato impiegato) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM impiegato WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, impiegato.getId());
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
