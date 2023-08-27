package hr.mev.zastita.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException e, Model model) {
        model.addAttribute("resource_error", "Traženi resurs nije pronađen.");
        return "error";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e, Model model) {
        model.addAttribute("constraint_error", "Korisnik ima reference u aplikaciji i ne može se izbrisati. Molimo pobrišite sva predavanja koja je kreirao korisnik ili odjavite sve ispite koje je korisnik prijavio!");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", "Došlo je do greške. Molimo provjerite da li ste ispravno unijeli sva polja!");
        return "error";
    }
}


