package hr.mev.zastita.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ispit")
public class Ispit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_ispit;
	
	@Column(name = "id_student")
    private long id_student;
	
	@Column
	private String naziv_ispit;
	
	@Column
	private Date datum_ispita;
	
	@OneToMany(mappedBy = "student")
	private List<Student> polozili;
	
	public Ispit() {
		super();
	}

	public Ispit(long id_ispit, long id_student, String naziv_ispit, Date datum_ispita, List<Student> polozili) {
		super();
		this.id_ispit = id_ispit;
		this.id_student = id_student;
		this.naziv_ispit = naziv_ispit;
		this.datum_ispita = datum_ispita;
		this.polozili = polozili;
	}

	public long getId_ispit() {
		return id_ispit;
	}

	public void setId_ispit(long id_ispit) {
		this.id_ispit = id_ispit;
	}

	public String getNaziv_ispit() {
		return naziv_ispit;
	}

	public void setNaziv_ispit(String naziv_ispit) {
		this.naziv_ispit = naziv_ispit;
	}

	public Date getDatum_ispita() {
		return datum_ispita;
	}

	public void setDatum_ispita(Date datum_ispita) {
		this.datum_ispita = datum_ispita;
	}

	public List<Student> getPolozili() {
		return polozili;
	}

	public void setPolozili(List<Student> polozili) {
		this.polozili = polozili;
	}

	public long getStudentId() {
		return id_student;
	}

	public void setStudentId(long id_student) {
		this.id_student = id_student;
	}
	
	
}
