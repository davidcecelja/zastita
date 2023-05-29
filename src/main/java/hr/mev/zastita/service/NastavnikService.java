package hr.mev.zastita.service;

import hr.mev.zastita.model.Nastavnik;

public interface NastavnikService {
	
	Nastavnik createNastavnik(Nastavnik nastavnik);

	Nastavnik updateNastavnik(Nastavnik nastavnik);
	
	Nastavnik getNastavnik(long id_nastavnik);
	
	Iterable<Nastavnik> getAllNastavnici();

	void deleteNastavnik(long id_nastavnik);
}
