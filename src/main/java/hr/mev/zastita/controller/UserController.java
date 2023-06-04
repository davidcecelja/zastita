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

import hr.mev.zastita.model.User;
import hr.mev.zastita.service.UserService;

@Controller
@RequestMapping("/index")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<User> popisKorisnika = (ArrayList<User>) service.getKorisnici();
		model.addAttribute("korisnici", popisKorisnika);
		return "korisnici";
	}
	 
	@GetMapping("/dodaj_korisnika")
	public String dodajUserGet(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "dodaj_korisnika";
	}
	
	@PostMapping("/dodaj_korisnika")
	public String dodajKorisnikaPost(@ModelAttribute("user") User user) {
		service.createUser(user);
		return "redirect:/korisnici/dodaj_korisnika"; 
	}
	
	@GetMapping("/uredi_korisnika/{username}")
	public ModelAndView urediKorisnikaGet(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("uredi_korisnika");
		User user = service.getUserByUsername(username);
		mav.addObject("user", user);
		return mav;
	}
	
	@PostMapping("/uredi_korisnika")
	public String spremiKorisnika(@ModelAttribute("user") User user) {
		service.updateUser(user);
		return "redirect:/korisnici/";
	}
	
	@GetMapping("/brisi/{korisnicko_ime}")
	public String brisiKorisnika(@PathVariable(name = "korisnicko_ime") String korisnicko_ime) {
		service.deleteUser(korisnicko_ime);
		return "redirect:/korisnici/";
	}
}
