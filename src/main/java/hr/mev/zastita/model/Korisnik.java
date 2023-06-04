package hr.mev.zastita.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name="korisnik")
@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String korisnicko_ime;
	
	@Column
	private String lozinka;

	@Column
	private String ime;

	@Column
	private String prezime;

	@Column
	private String email;

	@OneToMany
	private List<Uloga> uloge;

	public Korisnik() {
		super();
	}
	
	public Korisnik(long id, String korisnicko_ime, String lozinka, String ime, String prezime, String email,
			List<Uloga> uloge) {
		super();
		this.id = id;
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.uloge = uloge;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}

	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Uloga> getUloge() {
		return uloge;
	}

	public void setUloge(List<Uloga> uloge) {
		this.uloge = uloge;
	}
}
	
