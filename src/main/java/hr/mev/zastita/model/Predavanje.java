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

	@ManyToMany
	@JoinColumn(name = "kreirao_korisnik_id", referencedColumnName = "id")
	private Korisnik kreirao_korisnik;
}



