package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CodiceQR;
import persistence.dao.CodiceQRDao;

public class CodiceQRDaoJDBC implements CodiceQRDao {

	private DataSource dataSource;
	
	public CodiceQRDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(CodiceQR codice) {
		
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO codice_qr(id, orario_scadenza, convalida) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, codice.getCodice());
			statement.setString(2, codice.getScadenza());
			statement.setBoolean(3, codice.isConvalida());
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
	public CodiceQR findByPrimaryKey(String codice) {
		
		Connection connection = dataSource.getConnection();
		CodiceQR codiceQr = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM codice_qr WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				codiceQr = new CodiceQR();
				codiceQr.setCodice(result.getString("id"));
				codiceQr.setScadenza(result.getString("orario_Scadenza"));				
				codiceQr.setConvalida(result.getBoolean("convalida"));
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
		return codiceQr;
	}

	@Override
	public List<CodiceQR> findAll() {
		
		Connection connection = dataSource.getConnection();
		List<CodiceQR> codici = new ArrayList<>();
		CodiceQR c = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM codice_qr";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				c = new CodiceQR();
				c.setCodice(result.getString("id"));
				c.setScadenza(result.getString("orario_Scadenza"));				
				c.setConvalida(result.getBoolean("convalida"));
				codici.add(c);
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
		return codici;
	}

	@Override
	public void update(CodiceQR codice) {
		
		Connection connection = dataSource.getConnection();
		try {
			String update = "update codice_qr SET  convalida = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setBoolean(1, codice.isConvalida());
			statement.setString(2, codice.getCodice());
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
	public void delete(CodiceQR codice) {
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM codice_qr WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, codice.getCodice());
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
