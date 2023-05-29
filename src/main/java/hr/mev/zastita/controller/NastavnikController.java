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
	
	public String viewHomePage(Model model) {
		ArrayList<Nastavnik> popisNastavnika = (ArrayList<Nastavnik>) service.getAllNastavnici();
		model.addAttribute("nastavnici", popisNastavnika);
		return "nastavnici";
	}
	
	@RequestMapping(value = "/dodaj_nastavnika", method = RequestMethod.GET)
	public String noviNastavnikGet(Model model) {
		Nastavnik nastavnik = new Nastavnik();
		model.addAttribute("nastavnik", nastavnik);
		return "nastavnici";
	}
	
	@RequestMapping(value = "/novi", method = RequestMethod.POST)
	public String noviNastavnikPost(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
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
	
