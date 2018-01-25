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
					+ "drop table if EXISTS paziente;"
					+ "drop table if EXISTS università;"
					+ "drop table if EXISTS amministratore;"
					+ "drop table if EXISTS impiegato;"
					+ "drop table if EXISTS visitaMedica;"
					+ "drop table if EXISTS codiceQr;";
				
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
			String delete = "create SEQUENCE id;"
					+ "create table codiceQr(\"id\" VARCHAR(255) primary key, data_scadenza DATE, convalida BOOLEAN);"
					+ "create table università(matricola_p bigint primary key, nome_p VARCHAR(255), cognome_p VARCHAR(255));"
					+ "create table paziente(\"cf\" VARCHAR(255) primary key, nome VARCHAR(255),"
					+ "cognome VARCHAR(255), matricola bigint, invalidità VARCHAR(255), "
					+ "id_codiceQr VARCHAR(255) REFERENCES codiceQr(\"id\"), importo bigint);"
					+ "create table amministratore(\"username\" VARCHAR(255) primary key, password VARCHAR(255));"
					+ "create table visitaMedica(id_qr VARCHAR(255) REFERENCES codiceQr(\"id\"),"
					+ "nome_p VARCHAR(255), cognome_p VARCHAR(255));"
					+ "create table impiegato(\"id\" bigint primary key, username VARCHAR(255), password VARCHAR(255), ruolo VARCHAR(255));";
			
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
				
				delete = "delete FROM università";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM visitaMedica";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM codiceQr";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM amministratore";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM impiegato";
				statement = connection.prepareStatement(delete);
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
