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
			return korisnik.getLozinka().equals(spremljeniKorisnik.getLozinka());
		}
		return false;
	}

	@Override
	public void registracijaKorisnika(Korisnik korisnik) {
		korisnik.setEnabled(true);
		korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
		String email = korisnik.getEmail();
		String uloga;
		if (email.endsWith("@student.mev.hr")) {
            uloga = "STUDENT";
        } else if (email.endsWith("@mev.hr")) {
            uloga = "NASTAVNIK";
            }
       else {
            throw new IllegalStateException("Nepoznata uloga korisnika");
        }
		korisnik.setUloga(uloga);
	    repository.save(korisnik);
	}

	@Override
	public void odjavaKorisnika() {
		SecurityContextHolder.clearContext();
	}

	@Override
	public String prijavaKorisnika(String email, String lozinka) {
		 Korisnik korisnik = repository.findByEmail(email);
		    if (korisnik != null && lozinka.equals(korisnik.getLozinka())) {
		        String uloga = null;
		        
		        if (email.endsWith("@student.mev.hr")) {
		            uloga = "student";
		            return "redirect:/pocetna_student";
		        } else if (email.endsWith("@mev.hr")) {
		            uloga = "nastavnik";
		            return "redirect:/pocetna_nastavnik";
		        }
		    }
		    throw new BadCredentialsException("PogreĹˇna e-mail adresa ili lozinka");
	}
}
