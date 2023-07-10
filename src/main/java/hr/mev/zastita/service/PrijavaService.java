package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.Prijava;

public interface PrijavaService {

	Prijava createPrijava(Long idPredavanja);

	void updatePrijava(Prijava prijava);
	
	Prijava getPrijava(long id);
	
	List<Prijava> getPrijavaPoPredavanjeId(long predavanjeId);

	void deletePrijava(long id);
	
	Iterable<Prijava> getAllPrijave(); 

	void odjaviPrijavu(long id);
}
