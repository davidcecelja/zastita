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

import hr.mev.zastita.model.Predavanje;
import hr.mev.zastita.service.PredavanjeService;

@Controller
@RequestMapping("/predavanje")
public class PredavanjeController {
	
	@Autowired
	private PredavanjeService service;
	
	public String viewHomePage(Model model) {
		ArrayList<Predavanje> popisPredavanja = (ArrayList<Predavanje>) service.getAllPredavanja();
		model.addAttribute("predavanja", popisPredavanja);
		return "predavanja";
	}
	
	@RequestMapping(value = "/dodaj_predavanje", method = RequestMethod.GET)
	public String novoPredavanjeGet(Model model) {
		Predavanje predavanje = new Predavanje();
		model.addAttribute("predavanje", predavanje);
		return "predavanje";
	}
	
	// pitati chatgpt što znači što u kontroleru i kako je povezano s html th?
	// riješiti mode join ispit i student sukob u jpa
	
	
	@RequestMapping(value = "/novi", method = RequestMethod.POST)
	public String noviNastavnikPost(@ModelAttribute("nastavnik") Predavanje nastavnik) {
		service.createNastavnik(nastavnik);
		return "redirect:/nastavnik/";
	}
	
	@RequestMapping("(uredi_nastavnika/{id_nastavnik}")
	public ModelAndView pokaziUrediNastavnika(@PathVariable(name = "id_nastavnik") long id_nastavnik) {
		ModelAndView mav = new ModelAndView("uredi_nastavnika");
		Predavanje nastavnik = service.getNastavnik(id_nastavnik);
		mav.addObject("nastavnik", nastavnik);
		return mav;
	}
	
	@RequestMapping(value = "/uredi_nastavnika", method = RequestMethod.POST)
	public String spremiNastavnika(@ModelAttribute("nastavnik") Predavanje nastavnik) {
		service.updateNastavnik(nastavnik);
		return "redirect:/nastavnik/";
	}
	
	@RequestMapping("/brisi/{id_nastavnik}")
	public String brisiNastavnika(@PathVariable(name = "id_nastavnik") long id_nastavnika) {
		service.deleteNastavnik(id_nastavnika);
		return "redirect:/nastavnik/";
	}
}
