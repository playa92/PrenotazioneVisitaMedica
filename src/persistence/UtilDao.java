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
					+ "drop table if EXISTS paziente CASCADE;"
					+ "drop table if EXISTS università;"
					+ "drop table if EXISTS amministratore;"
					+ "drop table if EXISTS impiegato;"
					+ "drop table if EXISTS prenotazione CASCADE;"
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
					+ "create table codiceQr(id VARCHAR(255) primary key, orario_scadenza VARCHAR(255), valido BOOLEAN);"
					+ "create table università(matricola BIGINT primary key, nome_paziente VARCHAR(255), cognome_paziente VARCHAR(255));"
					+ "create table paziente(\"codice_fiscale\" VARCHAR(255) primary key, nome VARCHAR(255),"
					+ "cognome VARCHAR(255), matricola BIGINT, invalidità VARCHAR(255), "
					+ "id_codice VARCHAR(255) REFERENCES codiceQr(\"id\"));"
					+ "create table amministratore(username VARCHAR(255) primary key, password VARCHAR(255));"
					+ "create table prenotazione(id_visita VARCHAR(255) REFERENCES codiceQr(\"id\"),"
					+ "nome_paziente VARCHAR(255), cognome_paziente VARCHAR(255), orario_visita VARCHAR(255), importo BIGINT);"
					+ "create table impiegato(id BIGINT primary key, username VARCHAR(255), password VARCHAR(255), ruolo VARCHAR(255));";
			
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
				
				delete = "delete FROM prenotazione";
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
