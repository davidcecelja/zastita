package hr.mev.zastita.model;

import jakarta.persistence.*;

@Table(name="uloga")
@Entity
public class Uloga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String uloga;
	
	public Uloga() {
		super();
	}

	public Uloga(long id, String uloga) {
		super();
		this.id = id;
		this.uloga = uloga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
}
	
