package hr.mev.zastita.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.mev.zastita.exceptions.ResourceNotFoundException;

import hr.mev.zastita.model.User;
import hr.mev.zastita.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Override
	public List<User> getKorisnici() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {

		User userDB = this.userRepository.findByUsername(user.getKorisnicko_ime());
		if (userDB != null) {
	        userDB.setLozinka(user.getLozinka());
	        userDB.setUloge(user.getUloge());
	        userRepository.save(userDB);
	        return userDB;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + user.getKorisnicko_ime());
		}		
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}
