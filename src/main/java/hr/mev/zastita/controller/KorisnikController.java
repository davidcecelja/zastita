package hr.mev.zastita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.service.KorisnikService;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {
	
	@Autowired
	private KorisnikService service;
	
	@GetMapping("/")
	public String pocetnaStranica(Model model, Korisnik korisnik) {
		String uloga = korisnik.getUloga();
	       if(uloga.equals("student")) {
	    	   return "pocetna_student";
	       } else if(uloga.equals("nastavnik")) {
	    	   return "pocetna_nastavnik";
	       }
		return uloga;
	}
	
	@GetMapping("/novi")
	public String noviKorisnikGet(Model model) {
		Korisnik korisnik = new Korisnik();
		model.addAttribute("korisnik", korisnik);
		return "novi_korisnik";
	}
	
	@PostMapping("/novi")
	public String dodajKorisnikPost(@ModelAttribute("korisnik") Korisnik korisnik) {
		service.registracijaKorisnika(korisnik);
		return "redirect:/pocetna/"; 
	}
	
	@GetMapping("/uredi_korisnik/{id}")
	public ModelAndView urediKorisnikGet(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("uredi_korisnika");
		Korisnik korisnik = service.getKorisnik(id);
		mav.addObject("korisnik", korisnik);
		return mav;
	}
	
	@PostMapping("/uredi_korisnik")
	public String spremiKorisnika(@ModelAttribute("korisnik") Korisnik korisnik) {
		service.updateKorisnik(korisnik);
		return "redirect:/pocetna/";
	}
	
	@GetMapping("/brisi/{id}")
	public String brisiKorisnik(@PathVariable(name = "id") long id) {
		service.deleteKorisnik(id);
		return "redirect:/pocetna/";
	}
	
	@GetMapping("/registracija")
	public String prikaziFormuRegistracije(Model model) {
		model.addAttribute("korisnik", new Korisnik());
		return "korisnik";
	}
	
	@PostMapping("/registracija")
	public String registracijaKorisnika(Korisnik korisnik) {
		service.registracijaKorisnika(korisnik);
		return "redirect:/pocetna/novi_korisnik";
	}
	
	@GetMapping("/login")
	public String prikaziFormuPrijave() {
		return "login";
	}
	
	@PostMapping("/login")
	public String prijavaKorisnika(@RequestParam("email") String email, @RequestParam("lozinka") String lozinka) {
	    try {
	    	service.prijavaKorisnika(email, lozinka);
	        return "redirect:/login";
	    } catch (BadCredentialsException e) {
	        return "redirect:/pocetna/novi_korisnik";
	    }
	}
	
	@GetMapping("/odjava")
	public String odjavaKorisnika() {
		service.odjavaKorisnika();
	    return "redirect:/login";
	}
}

