package hr.mev.zastita.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Ispit;
import hr.mev.zastita.model.Student;
import hr.mev.zastita.service.StudentService;

@Controller
@RequestMapping("/index")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Student> popisStudenata = (ArrayList<Student>) service.getAllStudenti();
		model.addAttribute("student", popisStudenata);
		return "studenti";
	}
	 
	@GetMapping("/dodaj_studenta")
	public String dodajStudentaGet(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "dodaj_studenta";
	}
	
	@PostMapping("/dodaj_studenta")
	public String dodajStudentaPost(@ModelAttribute("student") Student student) {
		service.createStudent(student);
		return "redirect:/studenti/dodaj_studenta"; 
	}
	
	@GetMapping("/uredi_studenta/{id_student}")
	public ModelAndView urediStudentaGet(@PathVariable("id_student") long id_student) {
		ModelAndView mav = new ModelAndView("uredi_studenta");
		Student student = service.getStudent(id_student);
		mav.addObject("student", student);
		return mav;
	}
	
	@PostMapping("/uredi_studenta")
	public String spremiStudenta(@ModelAttribute("student") Student student, @RequestParam("polozili") List<Ispit> polozili) {
		student.setPolozili(polozili);
		service.updateStudent(student, polozili);
		return "redirect:/studenti/";
	}
	
	@GetMapping("/brisi/{id_student}")
	public String brisiStudenta(@PathVariable(name = "id_student") long id_student) {
		service.deleteStudent(id_student);
		return "redirect:/studenti/";
	}
}




	