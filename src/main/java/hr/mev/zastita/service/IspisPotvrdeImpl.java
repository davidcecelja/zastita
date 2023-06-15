package hr.mev.zastita.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.model.Predavanje;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class IspisPotvrdeImpl implements IspisPotvrde{

	@Override
	public void export(HttpServletResponse response, List<Predavanje> predavanja, List<Korisnik> prijavljeni_studenti) {
		
		
	}
}
