package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Prijava;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Long>{

}
