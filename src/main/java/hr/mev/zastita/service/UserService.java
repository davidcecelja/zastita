package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.User;

public interface UserService {
	
	List<User> getKorisnici();
    
    User getUserByUsername(String username);
    
    User createUser(User user);
    
    User updateUser(User user);
    
    void deleteUser(String korisnicko_ime);
}
