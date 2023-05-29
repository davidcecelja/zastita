package hr.mev.zastita.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

public class UserController {
	
	@RequestMapping(value = "/prijava", method = RequestMethod.POST)
	public ModelAndView prijavaPost(Model model, @ModelAttribute LoginData logData, HttpSession request)
	{
	// čitanje podatka o registraciji - super
	User user = this.userService.findByUserLogin(logData.getEmail(),logData.getLozinka());
	ModelAndView retVal = new ModelAndView();
	if (user == null ) {
	String messageError ="Greška: Neispravna korisnička oznaka ili zaporka.";
	model.addAttribute("loginError2", messageError);
	retVal.setViewName("/prijava");
	} else if (user.getId_user() <= 0 ) {
	String messageError ="Greška: Neispravna korisnička oznaka ili zaporka.";
	model.addAttribute("loginError2", messageError);
	model.addAttribute("loginError", true);
	retVal.setViewName("/prijava");
	}
	else if (user.getId_user() >0) {
	// sve je ispravno
	retVal.setViewName("redirect:/");
	}
	return retVal;
	}
}
