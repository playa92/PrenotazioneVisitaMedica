package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao {

	private DataSource dataSource;

	public UtilDao(DataSource dataSource) {
			this.dataSource=dataSource;
	}

	public void dropDatabase() throws PersistenceException {
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS id;"
					+ "drop table if exists paziente;";
				
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();
			System.out.println("Executed drop database");
			
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

	public void createDatabase() throws PersistenceException {
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "create SEQUENCE id; "
					+ "create table paziente(nome VARCHAR(255), cognome VARCHAR(255),"
					+ "\"matricola\" bigint primary key, invalidità VARCHAR(255));";
			PreparedStatement statement = connection.prepareStatement(delete);
	
			statement.executeUpdate();		
			System.out.println("Executed create database");
			
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


	public void resetDatabase() throws PersistenceException {
			
			Connection connection = dataSource.getConnection();
			try {
				String delete = "delete FROM paziente";
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
