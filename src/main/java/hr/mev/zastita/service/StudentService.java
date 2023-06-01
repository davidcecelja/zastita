package hr.mev.zastita.service;

import java.util.List;

import hr.mev.zastita.model.Ispit;
import hr.mev.zastita.model.Student;

public interface StudentService {
	
	Student createStudent(Student student);

	Student updateStudent(Student student, List<Ispit> polozili);
	
	Student getStudent(long id_student);

	void deleteStudent(long id_student);
	
	Iterable<Student> getAllStudenti();
}
