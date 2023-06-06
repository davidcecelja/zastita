package hr.mev.zastita.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="prijava")
public class Prijava {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_predavanje", referencedColumnName = "id")
	private Predavanje predavanje;

	@ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName= "id")
	private Korisnik student;

	@Column
	private Date datumPrijave;

	@Column
	private int ocjena;

	@Column
	private boolean polozeno;

	
}

	