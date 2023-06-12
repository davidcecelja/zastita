package hr.mev.zastita.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Prijava;
import hr.mev.zastita.service.PrijavaService;

public class PrijavaController {
	
	@Autowired
	private PrijavaService service;
	
	@GetMapping("prijava")
	public String viewHomePage(Model model) {
		ArrayList<Prijava> popisPrijava = (ArrayList<Prijava>) service.getAllPrijave();
		model.addAttribute("prijave", popisPrijava);
		return "prijave";
	}
	
	@GetMapping("/dodaj_prijavu")
	public String novaPrijavaGet(Model model) {
		Prijava prijava = new Prijava();
		model.addAttribute("prijava", prijava);
		return "prijava";
	}
	
	@PostMapping("/dodaj_prijavu")
	public String dodajPrijavuPost(@ModelAttribute("prijava") Prijava prijava) {
		service.createPrijava(prijava);
		return "redirect:/prijave/dodaj_prijavu"; 
	}
	
	@GetMapping("/uredi_prijavu/{id}")
	public ModelAndView urediPrijavuGet(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("uredi_prijavu");
		Prijava prijava = service.getPrijava(id);
		mav.addObject("prijava", prijava);
		return mav;
	}
	
	@PostMapping("/uredi_prijavu")
	public String spremiPrijavu(@ModelAttribute("prijava") Prijava prijava) {
		service.updatePrijava(prijava);
		return "redirect:/korisnici/";
	}
	
	@GetMapping("/brisi/{id}")
	public String brisiPrijavu(@PathVariable(name = "id") long id) {
		service.deletePrijava(id);
		return "redirect:/prijave/";
	}
}
