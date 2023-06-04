package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.Korisnik;

public interface UserService {
	
	List<Korisnik> getKorisnici();
    
    Korisnik getUserByUsername(String username);
    
    Korisnik createUser(Korisnik korisnik);
    
    Korisnik updateUser(Korisnik korisnik);
    
    void deleteUser(String korisnicko_ime);
}
