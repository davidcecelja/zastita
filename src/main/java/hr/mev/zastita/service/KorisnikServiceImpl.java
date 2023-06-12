package hr.mev.zastita.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService{
	
	private KorisnikRepository repository;

	@Override
	public Korisnik createKorisnik(Korisnik korisnik) {
		return repository.save(korisnik);
	}

	@Override
	public Korisnik updateKorisnik(Korisnik korisnik) throws ResourceNotFoundException {
		Optional<Korisnik> data = this.repository.findById(korisnik.getId());
		if(data.isPresent()) {
			Korisnik korisnikUpdate = data.get();
			korisnikUpdate.setIme(korisnik.getIme());
			korisnikUpdate.setPrezime(korisnik.getPrezime());
			korisnikUpdate.setEmail(korisnik.getEmail());
			korisnikUpdate.setLozinka(korisnik.getLozinka());
			korisnikUpdate.setUloga(korisnik.getUloga());
			korisnikUpdate.setPredavanja(korisnik.getPredavanja());
			repository.save(korisnikUpdate);
			return korisnikUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + korisnik.getId());
		}		
	}

	@Override
	public Korisnik getKorisnik(long id) {
		if(id == 0) {
			return new Korisnik();
		}
		Optional<Korisnik> data = this.repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		} else {
			return new Korisnik();
		}
	}

	@Override
	public void deleteKorisnik(long id) {
		Optional<Korisnik> data = this.repository.findById(id);
		if(data.isPresent()) {
			this.repository.delete(data.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}
	}

	@Override
	public Iterable<Korisnik> getAllKorisnik() {
		return this.repository.findAll();
	}
}
