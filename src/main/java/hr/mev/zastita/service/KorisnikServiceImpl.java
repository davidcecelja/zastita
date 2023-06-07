package hr.mev.zastita.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService{
	
	private KorisnikRepository repository;

	@Override
	public Korisnik createKorisnik(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik updateKorisnik(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik getKorisnik(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteKorisnik(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Korisnik> getAllKorisnik() {
		// TODO Auto-generated method stub
		return null;
	}

}
