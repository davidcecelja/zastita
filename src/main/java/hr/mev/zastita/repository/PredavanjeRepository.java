package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Predavanje;

@Repository
public interface PredavanjeRepository extends JpaRepository<Predavanje, Long>{

	@Query("SELECT p FROM Predavanje p WHERE p.status_predavanja = ?1")
	Iterable<Predavanje> findAllByStatusPredavanja(String status);
	
	@Query("SELECT p FROM Predavanje p WHERE p.kreirao_korisnik.email = :email")
	Iterable<Predavanje> findByKreiraoKorisnikEmail(@Param("email") String email);
	
}
