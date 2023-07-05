package hr.mev.zastita.controller;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.service.KorisnikService;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class KorisnikController {

    @Autowired
    private KorisnikService service;

    @GetMapping("/pocetna-student")
    public String pocetnaStranicaStudent(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        model.addAttribute("korisnici", new ArrayList<>());
        return "pocetna_student";
    }

    @GetMapping("/pocetna-nastavnik")
    public String pocetnaStranicaNastavnik(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        model.addAttribute("korisnici", new ArrayList<>());
        return "pocetna_nastavnik";
    }

    @GetMapping("/novi")
    public String noviKorisnikGet(Model model) {
        Korisnik korisnik = new Korisnik();
        model.addAttribute("korisnik", korisnik);
        return "pocetna_nastavnik";
    }

    @PostMapping("/novi")
    public String dodajKorisnikPost(@ModelAttribute("korisnik") Korisnik korisnik) {
        service.registracijaKorisnika(korisnik);
        return "redirect:/login/";
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
        return "redirect:/login/";
    }

    @GetMapping("/korisnik/brisi/{id}")
    public String brisiKorisnik(@PathVariable(name = "id") long id) {
        service.deleteKorisnik(id);
        return "redirect:/login/";
    }

    @GetMapping("/registracija")
    public String prikaziFormuRegistracije(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        return "korisnik";
    }

    @PostMapping("/registracija")
    public String registracijaKorisnika(Korisnik korisnik) {
        service.registracijaKorisnika(korisnik);
        return "redirect:/registracija";
    }

    @GetMapping("/login")
    public String prikaziFormuPrijave() {
        return "login";
    }

    @PostMapping("/login")
    public String prijavaKorisnika(@RequestBody Korisnik korisnik, RedirectAttributes redirectAttributes) {
        try {
            String email = korisnik.getEmail();
            String lozinka = korisnik.getLozinka();
            String uloga = service.prijavaKorisnika(email, lozinka);
            if (email.endsWith("@student.mev.hr")) {
	            uloga = "STUDENT";
	            return "redirect:/pocetna_student";
	        } else if (email.endsWith("@mev.hr")) {
	            uloga = "NASTAVNIK";
	            }
            if (uloga.equals("STUDENT")) {
                return "redirect:/pocetna_student";
            } else if (uloga.equals("NASTAVNIK")) {
                return "redirect:/pocetna_nastavnik";
            } else {
                throw new IllegalStateException("Nepoznata uloga korisnika");
            }
        } catch (BadCredentialsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Pogrešna e-mail adresa ili lozinka");
            return "redirect:/login";
        }
    }

    @GetMapping("/odjava")
    public String odjavaKorisnika() {
        service.odjavaKorisnika();
        return "redirect:/login";
    }
    
    @GetMapping("/potvrda")
    public ResponseEntity<byte[]> generateCertificate() throws IOException {
        // Dohvaćanje trenutno prijavljenog korisnika
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Generiranje sadržaja potvrde
        String certificateContent = "Ovo je potvrda da je korisnik " + username + " položio ispit iz Zaštite na radu.";

        // Generiranje PDF-a
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText(certificateContent);
        contentStream.endText();
        contentStream.close();

        // Pretvaranje PDF-a u niz bajtova
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        // Postavljanje zaglavlja za preuzimanje PDF-a
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificate.pdf");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}

