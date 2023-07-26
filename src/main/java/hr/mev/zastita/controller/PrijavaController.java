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
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Prijava;
import hr.mev.zastita.service.PrijavaService;

@Controller
public class PrijavaController {
	
	@Autowired
	private PrijavaService service;
	
	@GetMapping("/prijava")
	public String viewHomePage(Model model) {
		ArrayList<Prijava> popisPrijava = (ArrayList<Prijava>) service.getAllPrijave();
		model.addAttribute("prijave", popisPrijava);
		return "prijave";
	}
	
	@GetMapping("/prijave/{id}")
	public String otvoriStanicuSPrijavama(@PathVariable("id") long id, Model model) {
		List<Prijava> prijave = service.getPrijavaPoPredavanjeId(id);
		model.addAttribute("prijave", service.getPrijavaPoPredavanjeId(id));
		model.addAttribute("polozeno", prijave.stream().allMatch(Prijava::isPolozeno));
		return "prijave";		
	}
	
	@GetMapping("/dodaj_prijavu/{id}")
	public String novaPrijavaGet(@PathVariable("id") Long idPredavanja, Model model) {
		if(!model.containsAttribute("polozeno")) {
			service.createPrijava(idPredavanja);
		}				
		return "redirect:/pocetna-student";
	}
	
	@GetMapping("/uredi_prijava/{id}")
	public ModelAndView urediPrijavuGet(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("uredi_prijava");
		Prijava prijava = service.getPrijava(id);
		mav.addObject("prijava", prijava);
		return mav;
	}
	
	@PostMapping("/uredi_prijava")
	public String spremiPrijavu(@ModelAttribute("prijava") Prijava prijava) {
		service.updatePrijava(prijava);
		return "redirect:/prijave/" + prijava.getPredavanje().getId();
	}
	
	@GetMapping("/brisi_prijavu/{id}")
	public String brisiPrijavu(@PathVariable(name = "id") long id) {
		service.deletePrijava(id);
		return "redirect:/prijave/";
	}
	
	@GetMapping("/odjavi_prijavu/{id}")
	public String odjaviPrijavu(@PathVariable(name = "id") long id) {
		service.odjaviPrijavu(id);
		return "redirect:/pocetna-student";
	}
}
