package hr.mev.zastita.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="predavanje")
public class Predavanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_predavanje;
	
	@Column
	private String naziv_predavanja;
	
	@Column
	private Date datum_predavanja;
	
	@Column
	private int broj_studenata;
	
	public Predavanje() {
		super();
	}

	public Predavanje(long id_predavanje, String naziv_predavanja, Date datum_predavanja, int broj_studenata) {
		super();
		this.id_predavanje = id_predavanje;
		this.naziv_predavanja = naziv_predavanja;
		this.datum_predavanja = datum_predavanja;
		this.broj_studenata = broj_studenata;
	}

	public long getId_predavanje() {
		return id_predavanje;
	}

	public void setId_predavanje(long id_predavanje) {
		this.id_predavanje = id_predavanje;
	}

	public String getNaziv_predavanja() {
		return naziv_predavanja;
	}

	public void setNaziv_predavanja(String naziv_predavanja) {
		this.naziv_predavanja = naziv_predavanja;
	}

	public Date getDatum_predavanja() {
		return datum_predavanja;
	}

	public void setDatum_predavanja(Date datum_predavanja) {
		this.datum_predavanja = datum_predavanja;
	}

	public int getBroj_studenata() {
		return broj_studenata;
	}

	public void setBroj_studenata(int broj_studenata) {
		this.broj_studenata = broj_studenata;
	}
}
