package hr.mev.zastita.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="nastavnik")
public class Nastavnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_nastavnik;
	
	@Column
	private String ime_nastavnik;
	
	@Column
	private String prezime_nastavnik;
	
	@Column
	private String email_nastavnik;
	
	@Column
	private String lozinka_nastavnik;
	
	public Nastavnik() {
		super();
	}

	public Nastavnik(long id_nastavnik, String ime_nastavnik, String prezime_nastavnik, String email_nastavnik,
			String lozinka_nastavnik) {
		super();
		this.id_nastavnik = id_nastavnik;
		this.ime_nastavnik = ime_nastavnik;
		this.prezime_nastavnik = prezime_nastavnik;
		this.email_nastavnik = email_nastavnik;
		this.lozinka_nastavnik = lozinka_nastavnik;
	}

	public long getId_nastavnik() {
		return id_nastavnik;
	}

	public void setId_nastavnik(long id_nastavnik) {
		this.id_nastavnik = id_nastavnik;
	}

	public String getIme_nastavnik() {
		return ime_nastavnik;
	}

	public void setIme_nastavnik(String ime_nastavnik) {
		this.ime_nastavnik = ime_nastavnik;
	}

	public String getPrezime_nastavnik() {
		return prezime_nastavnik;
	}

	public void setPrezime_nastavnik(String prezime_nastavnik) {
		this.prezime_nastavnik = prezime_nastavnik;
	}

	public String getEmail_nastavnik() {
		return email_nastavnik;
	}

	public void setEmail_nastavnik(String email_nastavnik) {
		this.email_nastavnik = email_nastavnik;
	}

	public String getLozinka_nastavnik() {
		return lozinka_nastavnik;
	}

	public void setLozinka_nastavnik(String lozinka_nastavnik) {
		this.lozinka_nastavnik = lozinka_nastavnik;
	}
}
