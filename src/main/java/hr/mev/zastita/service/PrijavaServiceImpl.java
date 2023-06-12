package hr.mev.zastita.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Prijava;
import hr.mev.zastita.repository.PrijavaRepository;

@Service
@Transactional
public class PrijavaServiceImpl implements PrijavaService{
	
	private PrijavaRepository repository;

	@Override
	public Prijava createPrijava(Prijava prijava) {
		return repository.save(prijava);
	}

	@Override
	public Prijava updatePrijava(Prijava prijava) throws ResourceNotFoundException{
		Optional<Prijava> data = this.repository.findById(prijava.getId());
		if(data.isPresent()) {
			Prijava prijavaUpdate = data.get();
			prijavaUpdate.setStudent(prijava.getStudent());
			prijavaUpdate.setPredavanje(prijava.getPredavanje());
			prijavaUpdate.setDatumPrijave(prijava.getDatumPrijave());
			prijavaUpdate.setOcjena(prijava.getOcjena());
			prijavaUpdate.setPolozeno(prijava.isPolozeno());
			repository.save(prijavaUpdate);
			return prijavaUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + prijava.getId());
		}
	}

	@Override
	public Prijava getPrijava(long id) {
		if(id == 0) {
			return new Prijava();
		}
		Optional<Prijava> data = this.repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		} else {
			return new Prijava();
		}
	}

	@Override
	public void deletePrijava(long id) {
		Optional<Prijava> data = this.repository.findById(id);
		if(data.isPresent()) {
			this.repository.delete(data.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}
	}

	@Override
	public Iterable<Prijava> getAllPrijave() {
		return this.repository.findAll();
	}
}
