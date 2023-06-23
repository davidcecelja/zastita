package hr.mev.zastita.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService{
	
	@Autowired
	private KorisnikRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@Override
	public Korisnik findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public boolean autentifikacijaKorisnika(Korisnik korisnik) {
		Korisnik spremljeniKorisnik = repository.findByEmail(korisnik.getEmail());
		if (spremljeniKorisnik != null) {
			return passwordEncoder.matches(korisnik.getLozinka(), spremljeniKorisnik.getLozinka());
		}
		return false;
	}

	@Override
	public void registracijaKorisnika(Korisnik korisnik) {
		String enkriptiranaLozinka = passwordEncoder.encode(korisnik.getLozinka());
		korisnik.setLozinka(enkriptiranaLozinka);
		repository.save(korisnik);
	}

	@Override
	public void prijavaKorisnika(String email, String lozinka) {
		Korisnik korisnik = repository.findByEmail(email);
	    if (korisnik != null && passwordEncoder.matches(lozinka, korisnik.getLozinka())) {
	    	String uloga = korisnik.getUloga();
		if (email.endsWith("@student.mev.hr")) {
	            uloga = "student";
	        } else if (email.endsWith("@mev.hr")) {
	            uloga = "nastavnik";
	        } else {
	        	throw new BadCredentialsException("Pogrešna e-mail adresa ili lozinka");
	        }
	    } else {
	        throw new BadCredentialsException("Pogrešna e-mail adresa ili lozinka");
	    }
	}

	@Override
	public void odjavaKorisnika() {
		SecurityContextHolder.clearContext();
	}
}
