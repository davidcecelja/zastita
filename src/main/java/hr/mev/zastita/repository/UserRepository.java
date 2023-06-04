package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String korisnicko_ime);

	void delete(String korisnicko_ime);

}
