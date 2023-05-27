package hr.mev.zastita.service;

import java.util.List;
import java.util.Optional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Ispit;
import hr.mev.zastita.model.Student;
import hr.mev.zastita.repository.IspitRepository;

public class IspitServiceImpl implements IspitService{

	private IspitRepository ispitRepository;
	
	@Override
	public Ispit createIspit(Ispit ispit) {
		return ispitRepository.save(ispit);
	}

	@Override
	public Ispit updateIspit(Ispit ispit, List<Student> polozili) throws ResourceNotFoundException {
	
		Optional<Ispit> productDB = this.ispitRepository.findById(ispit.getId_ispit());
		if(productDB.isPresent()) {
			Ispit ispitUpdate = productDB.get();
			ispitUpdate.setNaziv_ispit(ispit.getNaziv_ispit());
			ispitUpdate.setDatum_ispita(ispit.getDatum_ispita());
			ispitUpdate.setPolozili(polozili);
			ispitRepository.save(ispitUpdate);
			return ispitUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + ispit.getId_ispit());
		}		
	}

	@Override
	public Ispit getIspit(long id_ispit) {
		if(id_ispit == 0)
			return new Ispit();
		
		Optional<Ispit> productDB = this.ispitRepository.findById(id_ispit);
		if(productDB.isPresent()) {
			return productDB.get();
		} else {
			return new Ispit();
		}
	}

	@Override
	public void deleteIspit(long id_ispit) {
		Optional<Ispit> productDB = this.ispitRepository.findById(id_ispit);
		if(productDB.isPresent()) {
			this.ispitRepository.delete(productDB.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen!");
		}
	}
}
