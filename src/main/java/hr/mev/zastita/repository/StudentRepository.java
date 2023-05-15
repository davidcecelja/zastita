package hr.mev.zastita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.mev.zastita.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
