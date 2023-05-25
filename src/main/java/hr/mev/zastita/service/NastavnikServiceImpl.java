package hr.mev.zastita.service;

import java.util.Optional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Nastavnik;
import hr.mev.zastita.repository.NastavnikRepository;

public class NastavnikServiceImpl implements NastavnikService{
	
	private NastavnikRepository nastavnikRepository;

	@Override
	public Nastavnik createNastavnik(Nastavnik nastavnik) {
		return nastavnikRepository.save(nastavnik);
	}

	@Override
	public Nastavnik updateNastavnik(Nastavnik nastavnik) {
		
		Optional<Nastavnik> productDB = this.nastavnikRepository.findById(nastavnik.getId_nastavnik());
		if(productDB.isPresent()) {
			Nastavnik nastavnikUpdate = productDB.get();
			nastavnikUpdate.setIme_nastavnik(nastavnik.getIme_nastavnik());
			nastavnikUpdate.setPrezime_nastavnik(nastavnik.getPrezime_nastavnik());
			nastavnikUpdate.setEmail_nastavnik(nastavnik.getEmail_nastavnik());
			nastavnikUpdate.setLozinka_nastavnik(nastavnik.getLozinka_nastavnik());
			nastavnikRepository.save(nastavnikUpdate);
			return nastavnikUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + nastavnik.getId_nastavnik());
		}		
	}

	@Override
	public Nastavnik getNastavnik(long id_nastavnik) {
		
		if(id_nastavnik == 0) {
			return new Nastavnik();
		}
		
		Optional<Nastavnik> productDB = this.nastavnikRepository.findById(id_nastavnik);
		if(productDB.isPresent()) {
			return productDB.get();
		} else {
			return new Nastavnik();
		}
	}

	@Override
	public void deleteNastavnik(long id_nastavnik) {
		Optional<Nastavnik> productDB = this.nastavnikRepository.findById(id_nastavnik);
		if(productDB.isPresent()) {
			this.nastavnikRepository.delete(productDB.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : ");
		}
	}
}
