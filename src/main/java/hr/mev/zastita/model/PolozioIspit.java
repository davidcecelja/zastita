package hr.mev.zastita.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="polozio_ispit")
public class PolozioIspit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_polozen;
	
	@ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName= "id_ispit")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name = "id_ispit", referencedColumnName= "id_student")
	private Ispit ispit;
	
	@Column
	private Date datumPolaganja;
	
	@Column
	private int ocjena;
	
	public PolozioIspit() {
		super();
	}

	public PolozioIspit(long id_polozen, Student student, Ispit ispit, Date datumPolaganja, int ocjena) {
		super();
		this.id_polozen = id_polozen;
		this.student = student;
		this.ispit = ispit;
		this.datumPolaganja = datumPolaganja;
		this.ocjena = ocjena;
	}

	public long getId_polozen() {
		return id_polozen;
	}

	public void setId_polozen(long id_polozen) {
		this.id_polozen = id_polozen;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Ispit getIspit() {
		return ispit;
	}

	public void setIspit(Ispit ispit) {
		this.ispit = ispit;
	}

	public Date getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setDatumPolaganja(Date datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

	public int getOcjena() {
		return ocjena;
	}

	public void setOcjena(int ocjena) {
		this.ocjena = ocjena;
	}
}
