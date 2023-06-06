package hr.mev.zastita.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="predavanje")
public class Predavanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String naziv_predavanja;

	@Column
	private String opis_predavanja;
	
	@Column
	private Date pocetak_predavanja;

	@Column
	private Date zavrsetak_predavanja;

	@Column
	private String status_predavanja;

	@ManyToMany
	private List<Korisnik> prijavljeni_studenti;

	@ManyToOne
	private Korisnik kreirao_korisnik;

	public Predavanje() {
		super();
	}

	public Predavanje(long id, String naziv_predavanja, String opis_predavanja, Date pocetak_predavanja,
			Date zavrsetak_predavanja, String status_predavanja, List<Korisnik> prijavljeni_studenti,
			Korisnik kreirao_korisnik) {
		super();
		this.id = id;
		this.naziv_predavanja = naziv_predavanja;
		this.opis_predavanja = opis_predavanja;
		this.pocetak_predavanja = pocetak_predavanja;
		this.zavrsetak_predavanja = zavrsetak_predavanja;
		this.status_predavanja = status_predavanja;
		this.prijavljeni_studenti = prijavljeni_studenti;
		this.kreirao_korisnik = kreirao_korisnik;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv_predavanja() {
		return naziv_predavanja;
	}

	public void setNaziv_predavanja(String naziv_predavanja) {
		this.naziv_predavanja = naziv_predavanja;
	}

	public String getOpis_predavanja() {
		return opis_predavanja;
	}

	public void setOpis_predavanja(String opis_predavanja) {
		this.opis_predavanja = opis_predavanja;
	}

	public Date getPocetak_predavanja() {
		return pocetak_predavanja;
	}

	public void setPocetak_predavanja(Date pocetak_predavanja) {
		this.pocetak_predavanja = pocetak_predavanja;
	}

	public Date getZavrsetak_predavanja() {
		return zavrsetak_predavanja;
	}

	public void setZavrsetak_predavanja(Date zavrsetak_predavanja) {
		this.zavrsetak_predavanja = zavrsetak_predavanja;
	}

	public String getStatus_predavanja() {
		return status_predavanja;
	}

	public void setStatus_predavanja(String status_predavanja) {
		this.status_predavanja = status_predavanja;
	}

	public List<Korisnik> getPrijavljeni_studenti() {
		return prijavljeni_studenti;
	}

	public void setPrijavljeni_studenti(List<Korisnik> prijavljeni_studenti) {
		this.prijavljeni_studenti = prijavljeni_studenti;
	}

	public Korisnik getKreirao_korisnik() {
		return kreirao_korisnik;
	}

	public void setKreirao_korisnik(Korisnik kreirao_korisnik) {
		this.kreirao_korisnik = kreirao_korisnik;
	}
}



