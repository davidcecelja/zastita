package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.model.Predavanje;
import jakarta.servlet.http.HttpServletResponse;

public interface IspisPotvrde {
	
	public void export(HttpServletResponse response, List<Predavanje> predavanja, List<Korisnik> prijavljeni_studenti);

}
