package hr.mev.zastita.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.model.Prijava;
import hr.mev.zastita.repository.PrijavaRepository;

@Service
@Transactional
public class PrijavaServiceImpl implements PrijavaService{
	
	private PrijavaRepository repository;

	@Override
	public Prijava createPrijava(Prijava prijava) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prijava updatePrijava(Prijava prijava) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prijava getPrijava(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrijava(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Prijava> getAllPrijave() {
		// TODO Auto-generated method stub
		return null;
	}

}
