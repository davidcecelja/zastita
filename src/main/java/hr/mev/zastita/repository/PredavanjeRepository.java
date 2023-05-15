package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Predavanje;

@Repository
public interface PredavanjeRepository extends JpaRepository<Predavanje, Long>{

}
