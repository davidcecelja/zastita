package hr.mev.zastita.model;

import jakarta.persistence.*;

@Table(name="uloga")
@Entity
public class Uloga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_uloga;

	@Column
	private String uloga;
	
	@ManyToMany(mappedBy = "clanovi", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Korisnik> korisnici = new HashSet<>();

	public long getId() {
		return id_uloga;
	}

	public void setId(long id) {
		this.id_uloga = id;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
}
	


	