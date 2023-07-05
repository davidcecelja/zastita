package hr.mev.zastita.service;

import hr.mev.zastita.model.Predavanje;

public interface PredavanjeService {
	
	Predavanje createPredavanje(Predavanje predavanje);

	Predavanje updatePredavanje(Predavanje predavanje);
	
	Predavanje getPredavanje(long id);

	void deletePredavanje(long id);
	
	Iterable<Predavanje> getAllPredavanja();
	
	Iterable<Predavanje> getAllNovaPredavanja();
}
