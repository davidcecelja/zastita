package hr.mev.zastita.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="polozio_ispit")
public class PolozioIspit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_polozen;
	
	@Column
	@ManyToMany
	private long id_student;
	
	@Column
	@OneToMany
	private long id_ispit;
	
	@Column
	private Date datumPolaganja;
	
	public PolozioIspit() {
		super();
	}

	public PolozioIspit(long id_polozen, long id_student, long id_ispit, Date datumPolaganja) {
		super();
		this.id_polozen = id_polozen;
		this.id_student = id_student;
		this.id_ispit = id_ispit;
		this.datumPolaganja = datumPolaganja;
	}

	public long getId_polozen() {
		return id_polozen;
	}

	public void setId_polozen(long id_polozen) {
		this.id_polozen = id_polozen;
	}

	public long getId_student() {
		return id_student;
	}

	public void setId_student(long id_student) {
		this.id_student = id_student;
	}

	public long getId_ispit() {
		return id_ispit;
	}

	public void setId_ispit(long id_ispit) {
		this.id_ispit = id_ispit;
	}

	public Date getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setDatumPolaganja(Date datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
}
