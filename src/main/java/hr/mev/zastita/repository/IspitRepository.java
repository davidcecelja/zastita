package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Ispit;

@Repository
public interface IspitRepository extends JpaRepository<Ispit, Long>{

}
