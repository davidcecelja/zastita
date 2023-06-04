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
import hr.mev.zastita.service.IspitService;


@Controller
@RequestMapping("/ispit")
public class IspitController {

	@Autowired
	private IspitService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Ispit> popisIspita = (ArrayList<Ispit>) service.getAllIspiti();
		model.addAttribute("ispiti", popisIspita);
		return "ispiti";
	}
	 
	@GetMapping("/dodaj_ispit")
	public String dodajIspitGet(Model model) {
		Ispit ispit = new Ispit();
		model.addAttribute("ispit", ispit);
		return "dodaj_ispit";
	}
	
	@PostMapping("/dodaj_ispit")
	public String dodajIspitPost(@ModelAttribute("ispit") Ispit ispit) {
		service.createIspit(ispit);
		return "redirect:/ispiti/dodaj_ispit"; 
	}
	
	@GetMapping("/uredi_ispit/{id_ispit}")
	public ModelAndView urediIspitGet(@PathVariable("id_ispit") long id_ispit) {
		ModelAndView mav = new ModelAndView("uredi_ispit");
		Ispit ispit = service.getIspit(id_ispit);
		mav.addObject("ispit", ispit);
		return mav;
	}
	
	@PostMapping("/uredi_ispit")
	public String spremiIspit(@ModelAttribute("ispit") Ispit ispit, @RequestParam("polozili") List<Ispit> polozili) {
		ispit.setPolozili(polozili);
		service.updateIspit(ispit, polozili);
		return "redirect:/ispiti/";
	}
	
	@GetMapping("/brisi/{id_ispit}")
	public String brisiIspit(@PathVariable(name = "id_ispit") long id_ispit) {
		service.deleteIspit(id_ispit);
		return "redirect:/ispiti/";
	}
}


	
