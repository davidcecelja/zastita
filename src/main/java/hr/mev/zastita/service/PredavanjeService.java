package hr.mev.zastita.service;

import hr.mev.zastita.model.Predavanje;

public interface PredavanjeService {
	
	Predavanje createPredavanje(Predavanje predavanje);

	Predavanje updatePredavanje(Predavanje predavanje);
	
	Predavanje getPredavanje(long id_predavanje);

	void deletePredavanje(long id_predavanje);
	
	Iterable<Predavanje> getAllPredavanja();
}
