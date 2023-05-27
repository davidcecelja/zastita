package hr.mev.zastita.service;

import java.util.List;
import java.util.Optional;

import hr.mev.zastita.exceptions.ResourceNotFoundException;
import hr.mev.zastita.model.Ispit;
import hr.mev.zastita.model.Student;
import hr.mev.zastita.repository.StudentRepository;

public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student, List<Ispit> polozili) throws ResourceNotFoundException{
		
		Optional<Student> productDB = this.studentRepository.findById(student.getId_student());
		if(productDB.isPresent()) {
			Student studentUpdate = productDB.get();
			studentUpdate.setIme_student(student.getIme_student());
			studentUpdate.setPrezime_student(student.getPrezime_student());
			studentUpdate.setGodina(student.getGodina());
			studentUpdate.setEmail_student(student.getEmail_student());
			studentUpdate.setStudij(student.getStudij());
			studentUpdate.setLozinka_student(student.getLozinka_student());
			studentUpdate.setPolozili(polozili);
			studentRepository.save(studentUpdate);
			return studentUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + student.getId_student());
		}		
	}

	@Override
	public Student getStudent(long id_student) {
		
		if(id_student == 0) {
			return new Student();
		}
		
		Optional<Student> productDB = this.studentRepository.findById(id_student);
		if(productDB.isPresent()) {
			return productDB.get();
		} else {
			return new Student();
		}
	}

	@Override
	public void deleteStudent(long id_student) {
		Optional<Student> productDB = this.studentRepository.findById(id_student);
		if(productDB.isPresent()) {
			this.studentRepository.delete(productDB.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen!");
		}
	}
}
