package hr.mev.zastita.controller;

import java.util.ArrayList;

import hr.mev.zastita.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.service.UserService;

@Controller
@RequestMapping("/index")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Korisnik> popisKorisnika = (ArrayList<Korisnik>) service.getKorisnici();
		model.addAttribute("korisnici", popisKorisnika);
		return "korisnici";
	}
	 
	@GetMapping("/dodaj_korisnika")
	public String dodajUserGet(Model model) {
		Korisnik korisnik = new Korisnik();
		model.addAttribute("user", korisnik);
		return "dodaj_korisnika";
	}
	
	@PostMapping("/dodaj_korisnika")
	public String dodajKorisnikaPost(@ModelAttribute("user") Korisnik korisnik) {
		service.createUser(korisnik);
		return "redirect:/korisnici/dodaj_korisnika"; 
	}
	
	@GetMapping("/uredi_korisnika/{username}")
	public ModelAndView urediKorisnikaGet(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("uredi_korisnika");
		Korisnik korisnik = service.getUserByUsername(username);
		mav.addObject("user", korisnik);
		return mav;
	}
	
	@PostMapping("/uredi_korisnika")
	public String spremiKorisnika(@ModelAttribute("user") Korisnik korisnik) {
		service.updateUser(korisnik);
		return "redirect:/korisnici/";
	}
	
	@GetMapping("/brisi/{korisnicko_ime}")
	public String brisiKorisnika(@PathVariable(name = "korisnicko_ime") String korisnicko_ime) {
		service.deleteUser(korisnicko_ime);
		return "redirect:/korisnici/";
	}
}
