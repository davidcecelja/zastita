package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.Korisnik;
import org.springframework.stereotype.Service;

import hr.mev.zastita.exceptions.ResourceNotFoundException;

import hr.mev.zastita.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Override
	public List<Korisnik> getKorisnici() {
		return userRepository.findAll();
	}

	@Override
	public Korisnik getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Korisnik createUser(Korisnik korisnik) {
		return userRepository.save(korisnik);
	}

	@Override
	public Korisnik updateUser(Korisnik korisnik) {

		Korisnik korisnikDB = this.userRepository.findByUsername(korisnik.getKorisnicko_ime());
		if (korisnikDB != null) {
	        korisnikDB.setLozinka(korisnik.getLozinka());
	        korisnikDB.setUloge(korisnik.getUloge());
	        userRepository.save(korisnikDB);
	        return korisnikDB;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + korisnik.getKorisnicko_ime());
		}		
	}

	@Override
	public void deleteUser(String korisnicko_ime) {
		userRepository.delete(korisnicko_ime);
		
	}
}
