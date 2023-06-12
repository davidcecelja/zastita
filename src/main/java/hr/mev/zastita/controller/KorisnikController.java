package hr.mev.zastita.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.service.KorisnikService;

public class KorisnikController {
	@Autowired
	private KorisnikService service;
	
	@GetMapping("korisnik")
	public String viewHomePage(Model model) {
		ArrayList<Korisnik> popisKorisnika = (ArrayList<Korisnik>) service.getAllKorisnik();
		model.addAttribute("korisnici", popisKorisnika);
		return "korisnici";
	}
	
	@GetMapping("/dodaj_korisnika")
	public String noviKorisnikGet(Model model) {
		Korisnik korisnik = new Korisnik();
		model.addAttribute("korisnik", korisnik);
		return "korisnik";
	}
	
	@PostMapping("/dodaj_korisnika")
	public String dodajKorisnikPost(@ModelAttribute("korisnik") Korisnik korisnik) {
		service.createKorisnik(korisnik);
		return "redirect:/korisnici/dodaj_korisnika"; 
	}
	
	@GetMapping("/uredi_korisnika/{id}")
	public ModelAndView urediKorisnikGet(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("uredi_korisnika");
		Korisnik korisnik = service.getKorisnik(id);
		mav.addObject("korisnik", korisnik);
		return mav;
	}
	
	@PostMapping("/uredi_korisnika")
	public String spremiKorisnika(@ModelAttribute("korisnik") Korisnik korisnik) {
		service.updateKorisnik(korisnik);
		return "redirect:/korisnici/";
	}
	
	@GetMapping("/brisi/{id}")
	public String brisiKorisnik(@PathVariable(name = "id") long id) {
		service.deleteKorisnik(id);
		return "redirect:/korisnici/";
	}
}
