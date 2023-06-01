package hr.mev.zastita.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Nastavnik;
import hr.mev.zastita.service.NastavnikService;

@Controller
@RequestMapping("/nastavnik")
public class NastavnikController {
	
	@Autowired
	private NastavnikService service;
	// metoda za pregled početne stranice gdje su prikazani svi nastavnici
	public String viewHomePage(Model model) {
		ArrayList<Nastavnik> popisNastavnika = (ArrayList<Nastavnik>) service.getAllNastavnici();
		model.addAttribute("nastavnici", popisNastavnika);
		return "nastavnici";
	}
	 // metoda za dodavanje novog nastavnika
	@RequestMapping(value = "/dodaj_nastavnika", method = RequestMethod.GET)
	public String dodajNastavnikaGet(Model model) {
		Nastavnik nastavnik = new Nastavnik();
		model.addAttribute("nastavnik", nastavnik);
		return "dodaj_nastavnika";
	}
	// metoda post za obradu podataka novog nastavnik koji su stigli metodo get
	@RequestMapping(value = "/dodaj_nastavnika", method = RequestMethod.POST)
	public String dodajNastavnikPost(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
		service.createNastavnik(nastavnik);
		return "redirect:/nastavnik/";
	}
	
	@RequestMapping("(uredi_nastavnika/{id_nastavnik}")
	public ModelAndView pokaziUrediNastavnika(@PathVariable(name = "id_nastavnik") long id_nastavnik) {
		ModelAndView mav = new ModelAndView("uredi_nastavnika");
		Nastavnik nastavnik = service.getNastavnik(id_nastavnik);
		mav.addObject("nastavnik", nastavnik);
		return mav;
	}
	
	@RequestMapping(value = "/uredi_nastavnika", method = RequestMethod.POST)
	public String spremiNastavnika(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
		service.updateNastavnik(nastavnik);
		return "redirect:/nastavnik/";
	}
	
	@RequestMapping("/brisi/{id_nastavnik}")
	public String brisiNastavnika(@PathVariable(name = "id_nastavnik") long id_nastavnika) {
		service.deleteNastavnik(id_nastavnika);
		return "redirect:/nastavnik/";
	}
}
	
