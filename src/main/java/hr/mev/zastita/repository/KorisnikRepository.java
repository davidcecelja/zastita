package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import hr.mev.zastita.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
	
	Korisnik findByEmail(String email);

	@Query("SELECT k FROM Korisnik k WHERE k.status = :status")
	Iterable<Korisnik> fetchUsers(@Param("status") String status);

}
