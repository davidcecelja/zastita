package hr.mev.zastita.controller;

import hr.mev.zastita.model.Korisnik;
import hr.mev.zastita.service.KorisnikService;
import hr.mev.zastita.service.PredavanjeService;
import hr.mev.zastita.service.PrijavaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Controller
public class KorisnikController {

    @Autowired
    private KorisnikService service;
    
    @Autowired
    private PredavanjeService predavanjeService;
    
    @Autowired
    private PrijavaService prijavaService;
    
    @GetMapping("/pocetna-student")
    public String pocetnaStranicaStudent(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailKorisnika = auth.getName();
		Korisnik trenutniKorisnik = service.findByEmail(emailKorisnika);
		
		model.addAttribute("korisnik", trenutniKorisnik);
        model.addAttribute("predavanja", predavanjeService.getAllPredavanja());
        model.addAttribute("prijave", prijavaService.getAllPrijave());
        model.addAttribute("prijavaService", prijavaService);
        
        return "pocetna_student";
    }

    @GetMapping("/pocetna-nastavnik")
    public String pocetnaStranicaNastavnik(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailKorisnika = auth.getName();
		Korisnik trenutniKorisnik = service.findByEmail(emailKorisnika);
    	
		model.addAttribute("korisnik", trenutniKorisnik);
    	model.addAttribute("predavanja", predavanjeService.getAllPredavanjaKreiraoNastavnik(emailKorisnika));
        return "pocetna_nastavnik";
    }
    
    @GetMapping("/pocetna-admin")
    public String pocetnaStranicaAdmin(Model model) {
    	model.addAttribute("korisnici", service.getAllKorisnik());
    	return "pocetna_admin";
    }
   
    @GetMapping("/novi")
    public String noviKorisnikGet(Model model) {
        Korisnik korisnik = new Korisnik();
        model.addAttribute("korisnik", korisnik);
        return "pocetna_admin";
    }

    @PostMapping("/novi")
    public String dodajKorisnikPost(@ModelAttribute("korisnik") Korisnik korisnik) {
        service.registracijaKorisnika(korisnik);
        return "redirect:/login/";
    }

    @GetMapping("/uredi_korisnik/{id}")
    public ModelAndView urediKorisnikGet(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("uredi_korisnik");
        Korisnik korisnik = service.getKorisnik(id);
        mav.addObject("korisnik", korisnik);
        return mav;
    }

    @PostMapping("/uredi-korisnik")
    public String spremiKorisnika(@ModelAttribute("korisnik") Korisnik korisnik) {
        service.updateKorisnik(korisnik);
        return "redirect:/pocetna-admin";
    }

    @GetMapping("/brisi_korisnik/{id}")
    public String brisiKorisnik(@PathVariable(name = "id") long id) {             
        service.deleteKorisnik(id);
        return "redirect:/pocetna-admin";
    }

    @GetMapping("/registracija")
    public String prikaziFormuRegistracije(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        model.addAttribute("greska", null); 
        return "registracija";
    }

    @PostMapping("/registracija")
    public String registracijaKorisnika(@Valid Korisnik korisnik, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {          
            return "registracija";
        }  
        String email = korisnik.getEmail();
        if(!email.matches("^.*@(mev\\.hr|student\\.mev\\.hr|admin\\.mev\\.hr)$")) {
        	model.addAttribute("greska", "Krivo upisan email!");
        	return "registracija";
        }
        service.registracijaKorisnika(korisnik);
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String prikaziFormuPrijave() {
        return "login";
    }

    @GetMapping("/odjava")
    public String odjavaKorisnika() {
        service.odjavaKorisnika();
        return "redirect:/login";
    }
    
   @GetMapping("/potvrda")
    public void generateCertificate(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String certificateContent = "Ovo je potvrda da je korisnik " + username + " poloĹľio ispit iz ZaĹˇtite na radu.";

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
     
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();
        
        byte[] b = baos.toByteArray();
        
        OutputStream os = response.getOutputStream();
        
        try {
        	os.write(b, 0, b.length);
        }catch (Exception e) {
        	
        }
        
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=potvrda.pdf");
    } 
}

