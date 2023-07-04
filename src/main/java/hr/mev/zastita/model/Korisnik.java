package hr.mev.zastita.model;

import java.util.List;

import jakarta.persistence.*;

@Table(name="korisnik")
@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String ime;
	
	@Column
	private String prezime;
	
	@Column
	private String lozinka;

	@Column
	private String email;
	
	@ManyToMany
	private List<Predavanje> predavanja;
	
	@Column
	private String uloga;
	
	public enum Uloga {
	    STUDENT,
	    NASTAVNIK
	}

	public Korisnik() {
		super();
	}

	public Korisnik(long id, String ime, String prezime, String lozinka, String email, List<Predavanje> predavanja,
			String uloga) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.email = email;
		this.predavanja = predavanja;
		this.uloga = uloga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Predavanje> getPredavanja() {
		return predavanja;
	}

	public void setPredavanja(List<Predavanje> predavanja) {
		this.predavanja = predavanja;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
}
	
