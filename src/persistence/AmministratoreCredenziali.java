package persistence;

import model.Amministratore;

public class AmministratoreCredenziali extends Amministratore {

	private DataSource dataSource;
	
	public AmministratoreCredenziali(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
