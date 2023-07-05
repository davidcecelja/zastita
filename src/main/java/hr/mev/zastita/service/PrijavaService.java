package hr.mev.zastita.service;

import hr.mev.zastita.model.Prijava;

public interface PrijavaService {

	Prijava createPrijava(Long idPredavanja);

	Prijava updatePrijava(Prijava prijava);
	
	Prijava getPrijava(long id);

	void deletePrijava(long id);
	
	Iterable<Prijava> getAllPrijave(); // ovo prof vidi

	void odjaviPrijavu(long id);
}
