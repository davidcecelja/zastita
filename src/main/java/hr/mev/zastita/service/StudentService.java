package hr.mev.zastita.service;

import hr.mev.zastita.model.Student;

public interface StudentService {
	
	Student createStudent(Student student);

	Student updateStudent(Student student);
	
	Student getStudent(long id_student);

	void deleteStudent(long id_student);
}
