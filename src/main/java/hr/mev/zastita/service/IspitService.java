package hr.mev.zastita.service;

import hr.mev.zastita.model.Ispit;

public interface IspitService {
	
	Ispit createIspit(Ispit ispit);

	Ispit updateIspit(Ispit ispit);
	
	Ispit getIspit(long id_ispit);

	void deleteIspit(long id_ispit);
}
