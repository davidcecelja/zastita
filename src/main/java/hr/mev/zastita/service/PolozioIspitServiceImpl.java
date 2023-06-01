package hr.mev.zastita.service;

import java.util.Optional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.PolozioIspit;
import hr.mev.zastita.repository.PolozioIspitRepository;

public class PolozioIspitServiceImpl implements PolozioIspitService{

	private PolozioIspitRepository polozioRepository;
	
	@Override
	public PolozioIspit createPolozio(PolozioIspit polozen) {
		return polozioRepository.save(polozen);
	}

	@Override
	public PolozioIspit updatePolozio(PolozioIspit polozen) {
		Optional<PolozioIspit> productDB = this.polozioRepository.findById(polozen.getId_polozen());
		if(productDB.isPresent()) {
			PolozioIspit polozioUpdate = productDB.get();
			polozioUpdate.setDatumPolaganja(polozen.getDatumPolaganja());
			polozioRepository.save(polozioUpdate);
			return polozioUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + polozen.getId_polozen());
		}		
	}

	@Override
	public PolozioIspit getPolozio(long id_polozen) {

		if(id_polozen == 0) 
			return new PolozioIspit();
		
		Optional<PolozioIspit> productDB = this.polozioRepository.findById(id_polozen);
		if(productDB.isPresent()) {
			return productDB.get();
		} else {
			return new PolozioIspit();
		}		
	}

	@Override
	public void deletePolozio(long id_polozen) {
		Optional<PolozioIspit> productDB = this.polozioRepository.findById(id_polozen);
		if(productDB.isPresent()) {
			this.polozioRepository.delete(productDB.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}
	}

	@Override
	public Iterable<PolozioIspit> getAllPolozeni() {
		return this.polozioRepository.findAll();
	}
}
