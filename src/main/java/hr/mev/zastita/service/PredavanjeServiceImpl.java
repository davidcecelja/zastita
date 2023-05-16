package hr.mev.zastita.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.model.Predavanje;
import hr.mev.zastita.repository.PredavanjeRepository;

@Service
@Transactional
public class PredavanjeServiceImpl implements PredavanjeService{
	
	private PredavanjeRepository predavanjeRepository;

	@Override
	public Predavanje createPredavanje(Predavanje predavanje) {
		return predavanjeRepository.save(predavanje);
	}

	@Override
	public Predavanje updatePredavanje(Predavanje predavanje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predavanje getPredavanje(long id_predavanje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePredavanje(long id_predavanje) {
		// TODO Auto-generated method stub
		
	}
}
