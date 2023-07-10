package hr.mev.zastita.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern="yyyy-MM-dd'T'hh:mm")
	private LocalDateTime pocetak_predavanja;

	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd'T'hh:mm")
	private LocalDateTime zavrsetak_predavanja;

	@Column
	private String status_predavanja;

	@ManyToMany
	private List<Korisnik> prijavljeni_studenti = new ArrayList<>();

	@ManyToOne
	private Korisnik kreirao_korisnik;

	public Predavanje() {
		super();
	}

	public Predavanje(long id, String naziv_predavanja, String opis_predavanja, LocalDateTime pocetak_predavanja,
			LocalDateTime zavrsetak_predavanja, String status_predavanja, List<Korisnik> prijavljeni_studenti,
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
	
	public enum PredavanjeStatus {
		NOVO,
		ZAVRSENO
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

	public LocalDateTime getPocetak_predavanja() {
		return pocetak_predavanja;
	}

	public void setPocetak_predavanja(LocalDateTime pocetak_predavanja) {
		this.pocetak_predavanja = pocetak_predavanja;
	}

	public LocalDateTime getZavrsetak_predavanja() {
		return zavrsetak_predavanja;
	}

	public void setZavrsetak_predavanja(LocalDateTime zavrsetak_predavanja) {
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

	@Override
	public String toString() {
		return "Predavanje [id=" + id + ", naziv_predavanja=" + naziv_predavanja + ", opis_predavanja="
				+ opis_predavanja + ", pocetak_predavanja=" + pocetak_predavanja + ", zavrsetak_predavanja="
				+ zavrsetak_predavanja + ", status_predavanja=" + status_predavanja + "]";
	}
}



