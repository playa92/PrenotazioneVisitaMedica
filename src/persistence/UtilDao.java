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
					+ "drop table if EXISTS logging;"
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
					+ "create table codice_qr(id VARCHAR(12) primary key, orario_scadenza VARCHAR(5), convalida BOOLEAN);"
					+ "create table università(matricola BIGINT primary key, nome_paziente VARCHAR(16), cognome_paziente VARCHAR(16));"
					+ "create table paziente(\"codice_fiscale\" VARCHAR(16) primary key, nome VARCHAR(16),"
					+ "cognome VARCHAR(16), matricola BIGINT, invalidità VARCHAR(16), "
					+ "id_codice VARCHAR(12) REFERENCES codice_qr(\"id\"));"
					+ "create table amministratore(username VARCHAR(16) primary key, password VARCHAR(16));"
					+ "create table prenotazione(id_prenotazione VARCHAR(12) primary key REFERENCES codice_qr(\"id\"),"
					+ "nome_paziente VARCHAR(16), cognome_paziente VARCHAR(16), orario_visita VARCHAR(5), importo BIGINT);"
					+ "create table impiegato(username VARCHAR(16) primary key, password VARCHAR(16), ruolo VARCHAR(16));"
					+ "create table segnalazione(id INT primary key, nome_utente VARCHAR(16), email VARCHAR(16),"
					+ "motivazione VARCHAR(255), commento VARCHAR(255), risposta VARCHAR(255), risolto BOOLEAN, mostra BOOLEAN);"
					+ "create table logging(id INT primary key, azione VARCHAR(16), data DATE, orario VARCHAR(8), nome_utente VARCHAR(16));";
			
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
				
				delete = "delete FROM prenotazione";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				delete = "delete FROM codice_qr";
				statement = connection.prepareStatement(delete);
				statement.executeUpdate();
				
				System.out.println("Executed reset database");
				
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
