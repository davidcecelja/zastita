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

import hr.mev.zastita.model.Nastavnik;
import hr.mev.zastita.service.NastavnikService;

public class PolozioIspitController {

}



@Controller
@RequestMapping("/index")
public class NastavnikController {
	
	@Autowired
	private NastavnikService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Nastavnik> popisNastavnika = (ArrayList<Nastavnik>) service.getAllNastavnici();
		model.addAttribute("nastavnici", popisNastavnika);
		return "nastavnici";
	}
	 
	@GetMapping("/dodaj_nastavnika")
	public String dodajNastavnikaGet(Model model) {
		Nastavnik nastavnik = new Nastavnik();
		model.addAttribute("nastavnik", nastavnik);
		return "dodaj_nastavnika";
	}
	
	@PostMapping("/dodaj_nastavnika")
	public String dodajNastavnikPost(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
		service.createNastavnik(nastavnik);
		return "redirect:/nastavnici/dodaj_nastavnika"; 
	}
	
	@GetMapping("/uredi_nastavnika/{id_nastavnik}")
	public ModelAndView urediNastavnikaGet(@PathVariable("id_nastavnik") long id_nastavnik) {
		ModelAndView mav = new ModelAndView("uredi_nastavnika");
		Nastavnik nastavnik = service.getNastavnik(id_nastavnik);
		mav.addObject("nastavnik", nastavnik);
		return mav;
	}
	
	@PostMapping("/uredi_nastavnika")
	public String spremiNastavnika(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
		service.updateNastavnik(nastavnik);
		return "redirect:/nastavnici/";
	}
	
	@GetMapping("/brisi/{id_nastavnik}")
	public String brisiNastavnika(@PathVariable(name = "id_nastavnik") long id_nastavnik) {
		service.deleteNastavnik(id_nastavnik);
		return "redirect:/nastavnici/";
	}
}