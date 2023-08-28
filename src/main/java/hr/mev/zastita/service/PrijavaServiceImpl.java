package hr.mev.zastita.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.model.Predavanje;
import hr.mev.zastita.model.Prijava;
import hr.mev.zastita.repository.PredavanjeRepository;
import hr.mev.zastita.repository.PrijavaRepository;

@Service
@Transactional
public class PrijavaServiceImpl implements PrijavaService{
	
	@Autowired
	private PrijavaRepository repository;
	
	@Autowired
	private PredavanjeRepository predavanjeRepository;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private PredavanjeService predavanjeService;

	@Override
	public Prijava createPrijava(Long predavanjeId) {
		Predavanje predavanje = predavanjeService.getPredavanje(predavanjeId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailKorisnika = auth.getName();
		Korisnik trenutniKorisnik = korisnikService.findByEmail(emailKorisnika);
		
		predavanje.getPrijavljeni_studenti().add(trenutniKorisnik);
		trenutniKorisnik.getPredavanja().add(predavanje);
		
		Prijava prijava = new Prijava();
		prijava.setDatumPrijave(LocalDateTime.now());
		prijava.setPredavanje(predavanje);
		prijava.setStudent(trenutniKorisnik);
		return repository.save(prijava);
	}

	@Override
	public void updatePrijava(Prijava prijava){
		repository.ocijeniPrijavu(prijava.getOcjena(), true, prijava.getId());
		
		if (prijava.getOcjena() >= 2 && prijava.getOcjena() <= 5) {
			prijava.setPolozeno(true);
		} else {
			prijava.setPolozeno(false);
		}		
		repository.save(prijava);
		
		long idPredavanje = prijava.getPredavanje().getId();
		
		int ocijenjeni = repository.brojOcijenjenihPrijava(idPredavanje);
		int neocijenjeni = repository.brojNeocijenjenihPrijava(idPredavanje);
		
		if(ocijenjeni == neocijenjeni){
			Predavanje predavanje = predavanjeService.getPredavanje(idPredavanje);
			predavanje.setStatus_predavanja("ZAVRSENO");
			predavanjeRepository.save(predavanje);
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

	@Override
	public void odjaviPrijavu(long predavanjeId) {
		Predavanje predavanje = predavanjeService.getPredavanje(predavanjeId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailKorisnika = auth.getName();
		Korisnik trenutniKorisnik = korisnikService.findByEmail(emailKorisnika);
		
		predavanje.getPrijavljeni_studenti().remove(trenutniKorisnik);
		trenutniKorisnik.getPredavanja().remove(predavanje);
		
		Prijava prijava = this.repository.findByPredavanje_idAndStudent_id(predavanjeId, trenutniKorisnik.getId());
		this.repository.deleteById(prijava.getId());
		
		System.out.println("prijava obrisana");
	}

	@Override
	public List<Prijava> getPrijavaPoPredavanjeId(long predavanjeId) {
		return repository.findByPredavanje_id(predavanjeId);
	}
}
