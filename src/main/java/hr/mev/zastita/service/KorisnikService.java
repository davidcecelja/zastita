package hr.mev.zastita.service;

import hr.mev.zastita.model.Korisnik;

public interface KorisnikService {

	Korisnik updateKorisnik(Korisnik korisnik);
	
	Korisnik getKorisnik(long id);

	void deleteKorisnik(long id);
	
	Iterable<Korisnik> getAllKorisnik();
	
	Korisnik findByEmail(String email);

	boolean autentifikacijaKorisnika(Korisnik korisnik);

	void registracijaKorisnika(Korisnik korisnik);

	void odjavaKorisnika();

}
