package hr.mev.zastita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Prijava;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Long>{

	Prijava findByPredavanje_idAndStudent_id(long predavanjeId, long id);
	
	List<Prijava> findByPredavanje_id(long predavanjeId);
	
	@Modifying
	@Query("UPDATE Prijava p SET p.ocjena = :ocjena, p.polozeno = :polozen WHERE id = :prijavaId")
	void ocijeniPrijavu(@Param("ocjena")int ocjena, @Param("polozen") boolean polozen,@Param("prijavaId") long prijavaId);
	
	@Query("SELECT COUNT(p) FROM Prijava p WHERE p.polozeno = 1 AND p.predavanje.id = :id_predavanje")
	int brojOcijenjenihPrijava(@Param("id_predavanje") long id_predavanje );
	
	@Query("SELECT COUNT(p) FROM Prijava p WHERE p.polozeno = 0 AND p.predavanje.id = :id_predavanje")
	int brojNeocijenjenihPrijava(@Param("id_predavanje") long id_predavanje);
	
	@Query("SELECT COUNT(p) FROM Prijava p WHERE p.predavanje.id = :id_predavanje")
	int brojPrijavljenihStudenata(@Param("id_predavanje") long id_predavanje);
}



