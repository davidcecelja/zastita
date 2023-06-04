package hr.mev.zastita.service;

import hr.mev.zastita.model.Prijava;

public interface PolozioIspitService {
	
	Prijava createPolozio(Prijava polozen);

	Prijava updatePolozio(Prijava polozen);
	
	Prijava getPolozio(long id_polozen);

	void deletePolozio(long id_polozen);
	
	Iterable<Prijava> getAllPolozeni();
}
