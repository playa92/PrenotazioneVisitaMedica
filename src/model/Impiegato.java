package model;

public class Impiegato {

	private Long id;
	private String username;
	private String password;
	private String ruolo;
	
	public Impiegato() {}
	
	public Impiegato(Long id, String username, String password, String ruolo) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
