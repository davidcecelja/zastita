package hr.mev.zastita.model;

import java.util.List;

public class User {
	
	private String korisnicko_ime;
	private String lozinka;
	private List<String> uloge;
	
	public User() {
		super();
	}

	public User(String korisnicko_ime, String lozinka, List<String> uloge) {
		super();
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		this.uloge = uloge;
	}

	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}

	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public List<String> getUloge() {
		return uloge;
	}

	public void setUloge(List<String> uloge) {
		this.uloge = uloge;
	}
}
	
