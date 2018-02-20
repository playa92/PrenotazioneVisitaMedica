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
					+ "drop table if EXISTS codice_qr CASCADE;"
					+ "drop table if EXISTS prenotazione;"
					+ "drop table if EXISTS segnalazione;";
				
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
					+ "create table codice_qr(id VARCHAR(255) primary key, orario_scadenza VARCHAR(255), convalida BOOLEAN);"
					+ "create table università(matricola BIGINT primary key, nome_paziente VARCHAR(255), cognome_paziente VARCHAR(255));"
					+ "create table paziente(\"codice_fiscale\" VARCHAR(255) primary key, nome VARCHAR(255),"
					+ "cognome VARCHAR(255), matricola BIGINT, invalidità VARCHAR(255), "
					+ "id_codice VARCHAR(255) REFERENCES codice_qr(\"id\"));"
					+ "create table amministratore(username VARCHAR(255) primary key, password VARCHAR(255));"
					+ "create table prenotazione(id_visita VARCHAR(255) primary key REFERENCES codice_qr(\"id\"),"
					+ "nome_paziente VARCHAR(255), cognome_paziente VARCHAR(255), orario_visita VARCHAR(255), importo BIGINT);"
					+ "create table impiegato(username VARCHAR(255) primary key, password VARCHAR(255), ruolo VARCHAR(255));"
					+ "create table segnalazione(id BIGINT primary key, email VARCHAR(255), nome_utente VARCHAR(255), "
					+ "motivazione VARCHAR(255), commento VARCHAR(255), risposta VARCHAR(255), risolto BOOLEAN, mostra BOOLEAN);";
			
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
				
				delete = "delete FROM codice_qr";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM amministratore";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM impiegato";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM segnalazione";
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
