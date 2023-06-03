package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.Ispit;

public interface IspitService {
	
	Ispit createIspit(Ispit ispit);

	Ispit updateIspit(Ispit ispit, List<Ispit> polozili);
	
	Ispit getIspit(long id_ispit);

	void deleteIspit(long id_ispit);
	
	Iterable<Ispit> getAllIspiti();
}
