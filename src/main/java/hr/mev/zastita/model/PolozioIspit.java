package hr.mev.zastita.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="polozio_ispit")
public class PolozioIspit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_polozen;
	
	@Column
	private Student student;
	
	@Column
	private Ispit ispit;
	
	@Column
	private LocalDateTime datumPolaganja;
	
	public PolozioIspit() {
		super();
	}

	public PolozioIspit(long id_polozen, Student student, Ispit ispit, LocalDateTime datumPolaganja) {
		super();
		this.id_polozen = id_polozen;
		this.student = student;
		this.ispit = ispit;
		this.datumPolaganja = datumPolaganja;
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

	public LocalDateTime getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setDatumPolaganja(LocalDateTime datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
}
