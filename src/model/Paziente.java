package model;

public class Paziente {

	private String name;
	private String surname;
	private Long id;
	private String invalidity;
	
	public Paziente() {}
	
	public Paziente(String name, String surname, Long id, String invalidity) {
		
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.invalidity = invalidity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvalidity() {
		return invalidity;
	}

	public void setInvalidity(String invalidity) {
		this.invalidity = invalidity;
	}
}
