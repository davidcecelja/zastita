package hr.mev.zastita.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Predavanje;
import hr.mev.zastita.service.PredavanjeService;

@Controller
@RequestMapping("/index")
public class PredavanjeController {
	
	@Autowired
	private PredavanjeService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Predavanje> popisPredavanja = (ArrayList<Predavanje>) service.getAllPredavanja();
		model.addAttribute("predavanja", popisPredavanja);
		return "predavanja";
	}
	
	@GetMapping("/dodaj_predavanje")
	public String novoPredavanjeGet(Model model) {
		Predavanje predavanje = new Predavanje();
		model.addAttribute("predavanje", predavanje);
		return "dodaj_predavanje";
	}
	
	@PostMapping("/dodaj_predavanje")
	public String dodajPredavanjePost(@ModelAttribute("predavanje") Predavanje predavanje) {
		service.createPredavanje(predavanje);
		return "redirect:/predavanja/dodaj_predavanja"; 
	}
	
	@GetMapping("/uredi_predavanje/{id_predavanje}")
	public ModelAndView urediPredavanjeGet(@PathVariable("id_predavanje") long id_predavanje) {
		ModelAndView mav = new ModelAndView("uredi_predavanje");
		Predavanje predavanje = service.getPredavanje(id_predavanje);
		mav.addObject("predavanje", predavanje);
		return mav;
	}
	
	@PostMapping("/uredi_predavanje")
	public String spremiPredavanje(@ModelAttribute("predavanje") Predavanje predavanje) {
		service.updatePredavanje(predavanje);
		return "redirect:/predavanja/";
	}
	
	@GetMapping("/brisi/{id_predavanje}")
	public String brisiPredavanje(@PathVariable(name = "id_predavanje") long id_predavanje) {
		service.deletePredavanje(id_predavanje);
		return "redirect:/predavanje/";
	}
}
