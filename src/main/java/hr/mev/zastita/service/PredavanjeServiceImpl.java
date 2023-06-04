package hr.mev.zastita.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
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
	public Predavanje updatePredavanje(Predavanje predavanje) throws ResourceNotFoundException {
		
		Optional<Predavanje> productDB = this.predavanjeRepository.findById(predavanje.getId_predavanje());
		if(productDB.isPresent()) {
			Predavanje predavanjeUpdate = productDB.get();
			predavanjeUpdate.setNaziv_predavanja(predavanje.getNaziv_predavanja());
			predavanjeUpdate.setGodina_predavanja(predavanje.getGodina_predavanja());
			predavanjeUpdate.setKreirao_korisnik(predavanje.getKreirao_korisnik());
			predavanjeUpdate.setPocetak_predavanja(predavanje.getPocetak_predavanja());
			predavanjeUpdate.setZavrsetak_predavanja(predavanje.getZavrsetak_predavanja());
			predavanjeUpdate.setStatus_predavanja(predavanje.getStatus_predavanja());
			predavanjeUpdate.setPrijavljeni_studenti(predavanje.getPrijavljeni_studenti());
			predavanjeUpdate.setOpis_predavanja(predavanje.getOpis_predavanja());
			predavanjeRepository.save(predavanjeUpdate);
			return predavanjeUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + predavanje.getId_predavanje());
		}		
	}

	@Override
	public Predavanje getPredavanje(long id_predavanje) {
		
		if(id_predavanje == 0) 
			return new Predavanje();
		
		Optional<Predavanje> productDB = this.predavanjeRepository.findById(id_predavanje);
		if(productDB.isPresent()) {
			return productDB.get();
		} else {
			return new Predavanje();
		}		
	}

	@Override
	public void deletePredavanje(long id_predavanje) {
		Optional<Predavanje> productDB = this.predavanjeRepository.findById(id_predavanje);
		if(productDB.isPresent()) {
			this.predavanjeRepository.delete(productDB.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}
	}

	@Override
	public Iterable<Predavanje> getAllPredavanja() {
		return this.predavanjeRepository.findAll();
	}
}
