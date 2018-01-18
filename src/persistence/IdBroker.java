package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdBroker {

	private static final String query = "SELECT nextval('id') AS matricola";

	public static Long getId(Connection connection) throws PersistenceException {
		
		Long id = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("matricola");
			
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return id;
	}
}
