package hr.mev.zastita.service;

import java.util.Optional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Prijava;
import hr.mev.zastita.repository.PolozioIspitRepository;

public class PolozioIspitServiceImpl implements PolozioIspitService{

	private PolozioIspitRepository polozioRepository;
	
	@Override
	public Prijava createPolozio(Prijava polozen) {
		return polozioRepository.save(polozen);
	}

	@Override
	public Prijava updatePolozio(Prijava polozen) {
		Optional<Prijava> productDB = this.polozioRepository.findById(polozen.getId_polozen());
		if(productDB.isPresent()) {
			Prijava polozioUpdate = productDB.get();
			polozioUpdate.setDatumPolaganja(polozen.getDatumPolaganja());
			polozioRepository.save(polozioUpdate);
			return polozioUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + polozen.getId_polozen());
		}		
	}

	@Override
	public Prijava getPolozio(long id_polozen) {

		if(id_polozen == 0) 
			return new Prijava();
		
		Optional<Prijava> productDB = this.polozioRepository.findById(id_polozen);
		if(productDB.isPresent()) {
			return productDB.get();
		} else {
			return new Prijava();
		}		
	}

	@Override
	public void deletePolozio(long id_polozen) {
		Optional<Prijava> productDB = this.polozioRepository.findById(id_polozen);
		if(productDB.isPresent()) {
			this.polozioRepository.delete(productDB.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}
	}

	@Override
	public Iterable<Prijava> getAllPolozeni() {
		return this.polozioRepository.findAll();
	}
}
