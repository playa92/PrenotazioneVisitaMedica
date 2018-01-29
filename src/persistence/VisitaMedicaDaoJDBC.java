package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Paziente;
import persistence.dao.VisitaMedicaDao;

public class VisitaMedicaDaoJDBC implements VisitaMedicaDao {

	private DataSource dataSource;
	
	public VisitaMedicaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Paziente paziente) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "insert INTO visitaMedica(id_visita, nome_paziente, cognome_paziente, importo) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, paziente.getCodiceQR().getCodice());
			statement.setString(2, paziente.getNome());
			statement.setString(3, paziente.getCognome());
			statement.setDouble(4, paziente.getImporto());
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
	public Paziente findByPrimaryKey(String codiceQr) {
		Connection connection = this.dataSource.getConnection();
		Paziente paziente = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM visitaMedica WHERE id_Visita = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codiceQr);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				paziente = new Paziente();
				paziente.setCodiceFiscale(result.getString("codice_fiscale"));
				paziente.setNome(result.getString("nome"));				
				paziente.setCognome(result.getString("cognome"));
				paziente.setMatricola(result.getLong("matricola"));
				paziente.setInvalidita(result.getString("invalidità"));
//				paziente.setCodice((CodiceQR) result.getObject("id_codice"));
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
		List<Paziente> pazienti = new LinkedList<>();
		Paziente paziente = null;
		try {
			PreparedStatement statement;
			String query = "select * FROM visitaMedica";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				paziente = new Paziente();
				paziente.setCodiceFiscale(result.getString("id_visita"));
				paziente.setNome(result.getString("nome"));				
				paziente.setCognome(result.getString("cognome"));
//				paziente.setMatricola(result.getLong("matricola"));
				paziente.setImporto(result.getDouble("importo"));
//				paziente.setInvalidita(result.getString("invalidità"));
//				paziente.setCodice((CodiceQR) result.getObject("id_codice"));
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
	public void update(Paziente paziente) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update visitaMedica SET id_visita = ?, nome_paziente = ?, cognome_paziente = ?, importo = ?, WHERE id_visita = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, paziente.getCodiceQR().getCodice());
			statement.setString(2, paziente.getNome());
			statement.setString(3, paziente.getCognome());
			statement.setDouble(4, paziente.getImporto());
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
	public void delete(Paziente paziente) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM visitaMedica WHERE id_visita = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, paziente.getCodiceQR().getCodice());
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
	public int getTotalVisits() {
		
		int total = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select COUNT(*) AS total FROM visitaMedica";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				total = result.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			
			try {
				connection.close();
			} catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return total;
	}

}
