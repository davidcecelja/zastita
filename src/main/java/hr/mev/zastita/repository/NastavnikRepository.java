package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Nastavnik;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Long>{
	
}
