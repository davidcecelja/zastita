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
	
	@PrePersist
	public void prePersist() {
	      
	    if (email.endsWith("@student.mev.hr")) {
	         uloga = "student";
	    }else if (email.endsWith("@mev.hr")) {
	         uloga = "nastavnik";
	    }
	}
	
	@ManyToMany
	private List<Predavanje> predavanja;
	
	@Column
	private String uloga;
}
	
