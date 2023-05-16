package hr.mev.zastita.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ispit")
public class Ispit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_ispit;
	
	@Column
	private String naziv_ispit;
	
	@Column
	private Date datum_ispita;
	
	public Ispit() {
		super();
	}

	public Ispit(long id_ispit, String naziv_ispit, Date datum_ispita) {
		super();
		this.id_ispit = id_ispit;
		this.naziv_ispit = naziv_ispit;
		this.datum_ispita = datum_ispita;
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
}
