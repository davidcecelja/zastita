package hr.mev.zastita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pocetna")
@Controller
public class WebController {

	@GetMapping
	public String PocetnaStranica() {
		return "pocetna";
	}
}

