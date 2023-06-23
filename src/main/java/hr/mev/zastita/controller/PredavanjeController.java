package hr.mev.zastita.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Predavanje;
import hr.mev.zastita.service.PredavanjeService;

@Controller
public class PredavanjeController {
	
	@Autowired
	private PredavanjeService service;
	
	@GetMapping("/predavanje")
	public String viewHomePage(Model model) {
		ArrayList<Predavanje> popisPredavanja = (ArrayList<Predavanje>) service.getAllPredavanja();
		model.addAttribute("predavanja", popisPredavanja);
		return "predavanja";
	}
	
	@GetMapping("/dodaj_predavanje")
	public String novoPredavanjeGet(Model model) {
		Predavanje predavanje = new Predavanje();
		model.addAttribute("predavanje", predavanje);
		return "predavanja";
	}
	
	@PostMapping("/dodaj_predavanje")
	public String dodajPredavanjePost(@ModelAttribute("predavanje") Predavanje predavanje) {
		service.createPredavanje(predavanje);
		return "redirect:/predavanja/"; 
	}
	
	@GetMapping("/uredi_predavanje/{id}")
	public ModelAndView urediPredavanjeGet(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("uredi_predavanje");
		Predavanje predavanje = service.getPredavanje(id);
		mav.addObject("predavanje", predavanje);
		return mav;
	}
	
	@PostMapping("/uredi_predavanje")
	public String spremiPredavanje(@ModelAttribute("predavanje") Predavanje predavanje) {
		service.updatePredavanje(predavanje);
		return "redirect:/predavanja/";
	}
	
	@GetMapping("/brisi/{id}")
	public String brisiPredavanje(@PathVariable(name = "id") long id) {
		service.deletePredavanje(id);
		return "redirect:/predavanja/";
	}
}
