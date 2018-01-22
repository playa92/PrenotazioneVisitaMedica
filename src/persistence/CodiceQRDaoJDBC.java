package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert INTO codiceQr(id, data_scadenza, convalida) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, codice.getCodice());
			long secs = codice.getScadenza().getTime();
			statement.setDate(2, new Date(secs));
			statement.setBoolean(3, codice.getValido());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodiceQR> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CodiceQR codice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CodiceQR codice) {
		// TODO Auto-generated method stub
		
	}
}
