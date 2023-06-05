package hr.mev.zastita.model;

import jakarta.persistence.*;

import java.util.Set;

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

	@Transient
	private String email;
	
	@PrePersist
	public void prePersist() {
	      
	    if (email.endsWith("@student.mev.hr")) {
	         uloga = "student";
	    }else if (email.endsWith("@mev.hr")) {
	         uloga = "nastavnik";
	    }
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "korisnik_uloga", joinColumns = @JoinColumn(name = "id_korisnik"), inverseJoinColumns = @JoinColumn(name = "id_uloga"))
	private Set<Uloga> uloge;

	
}
	
