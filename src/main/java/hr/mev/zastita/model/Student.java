package hr.mev.zastita.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_student;
	
	@Column(name = "id_ispit")
    private long id_ispit;
	
	@Column
	private String ime_student;
	
	@Column
	private String prezime_student;
	
	@Column
	private String email_student;
	
	@Column
	private String lozinka_student;
	
	@Column
	private String studij;
	
	@Column
	private int godina;
	
	@OneToMany(mappedBy = "student")
	private List<Ispit> polozili;
	
	public Student() {
		super();
	}
	
	public Student(long id_student, long id_ispit, String ime_student, String prezime_student, String email_student,
			String lozinka_student, String studij, int godina, List<Ispit> polozili) {
		super();
		this.id_student = id_student;
		this.id_ispit = id_ispit;
		this.ime_student = ime_student;
		this.prezime_student = prezime_student;
		this.email_student = email_student;
		this.lozinka_student = lozinka_student;
		this.studij = studij;
		this.godina = godina;
		this.polozili = polozili;
	}

	public long getId_student() {
		return id_student;
	}
	
	public void setId_student(long id_student) {
		this.id_student = id_student;
	}
	
	public String getIme_student() {
		return ime_student;
	}
	
	public void setIme_student(String ime_student) {
		this.ime_student = ime_student;
	}
	
	public String getPrezime_student() {
		return prezime_student;
	}
	
	public void setPrezime_student(String prezime_student) {
		this.prezime_student = prezime_student;
	}
	
	public String getEmail_student() {
		return email_student;
	}
	
	public void setEmail_student(String email_student) {
		this.email_student = email_student;
	}
	
	public String getLozinka_student() {
		return lozinka_student;
	}
	
	public void setLozinka_student(String lozinka_student) {
		this.lozinka_student = lozinka_student;
	}

	public String getStudij() {
		return studij;
	}

	public void setStudij(String studij) {
		this.studij = studij;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public List<Ispit> getPolozili() {
		return polozili;
	}

	public void setPolozili(List<Ispit> polozili) {
		this.polozili = polozili;
	}

	public long getId_ispit() {
		return id_ispit;
	}

	public void setId_ispit(long id_ispit) {
		this.id_ispit = id_ispit;
	}
}
