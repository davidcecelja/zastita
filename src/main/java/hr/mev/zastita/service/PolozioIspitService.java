package hr.mev.zastita.service;

import hr.mev.zastita.model.PolozioIspit;

public interface PolozioIspitService {
	
	PolozioIspit createPolozio(PolozioIspit polozen);

	PolozioIspit updatePolozio(PolozioIspit polozen);
	
	PolozioIspit getPolozio(long id_polozen);

	void deletePolozio(long id_polozen);
}
