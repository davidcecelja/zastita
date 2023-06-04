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
	private Date datumOcjenjivanja;
	
	@Column
	private int ocjena;

	@Column
	private boolean polozeno;

	public Prijava() {
		super();
	}

	public Prijava(long id, Predavanje predavanje, Korisnik student, Date datumPrijave, Date datumOcjenjivanja,
			int ocjena, boolean polozeno) {
		super();
		this.id = id;
		this.predavanje = predavanje;
		this.student = student;
		this.datumPrijave = datumPrijave;
		this.datumOcjenjivanja = datumOcjenjivanja;
		this.ocjena = ocjena;
		this.polozeno = polozeno;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Predavanje getPredavanje() {
		return predavanje;
	}

	public void setPredavanje(Predavanje predavanje) {
		this.predavanje = predavanje;
	}

	public Korisnik getStudent() {
		return student;
	}

	public void setStudent(Korisnik student) {
		this.student = student;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public Date getDatumOcjenjivanja() {
		return datumOcjenjivanja;
	}

	public void setDatumOcjenjivanja(Date datumOcjenjivanja) {
		this.datumOcjenjivanja = datumOcjenjivanja;
	}

	public int getOcjena() {
		return ocjena;
	}

	public void setOcjena(int ocjena) {
		this.ocjena = ocjena;
	}

	public boolean isPolozeno() {
		return polozeno;
	}

	public void setPolozeno(boolean polozeno) {
		this.polozeno = polozeno;
	}
}
