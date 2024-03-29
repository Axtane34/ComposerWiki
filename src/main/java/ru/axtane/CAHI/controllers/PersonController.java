package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.dto.ComposerDTO;
import ru.axtane.CAHI.dto.PersonDTO;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.models.enums.AccessLevel;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.EssaysService;
import ru.axtane.CAHI.services.PeopleService;
import ru.axtane.CAHI.services.RegistrationService;
import ru.axtane.CAHI.util.PersonValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/CAHI")
public class PersonController {
    private final PeopleService peopleService;
    private final ComposersService composersService;
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final EssaysService essaysService;

    @Autowired
    public PersonController(PeopleService peopleService, ComposersService composersService, RegistrationService registrationService, PersonValidator personValidator, EssaysService essaysService) {
        this.peopleService = peopleService;
        this.composersService = composersService;
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.essaysService = essaysService;
    }

    @GetMapping("/registration")
    public String newAccount(@ModelAttribute("person") Person person){
        return "account/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "account/registration";
        }
        registrationService.register(person);
        return "redirect:/CAHI/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "account/login";
    }

    @GetMapping("/loginAsGuest")
    public String loginAsGuestPage(HttpServletRequest request){
        Person person = peopleService.findByLogin("guest");
        if (person!=null){
            try {
                request.login("guest", "guest");
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else {
            person = new Person("guest", "guest", "guest@gmail.com", AccessLevel.ROLE_GUEST);
            registrationService.register(person);
            try {
                request.login("guest", "guest");
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/CAHI/guest";
    }

    @GetMapping("/account")
    public String show(Model person) {
        if (getPerson().getUsername().equals("guest")){
            return "redirect:/CAHI/guest";
        }else {
            Person proxy = peopleService.findByLogin(getPerson().getUsername());
            person.addAttribute("publications", peopleService.findAllWithEnum(PublicationStatus.PUBLISHED, proxy));
            person.addAttribute("userComposers", peopleService.findComposersWithEnum(PublicationStatus.PUBLISHED, proxy));
            person.addAttribute("composers", composersService.findAll());
            return "account/user";
        }
    }

    @GetMapping("/adminPanel")
    public String showAdminPanel(Model model) {
        model.addAttribute("composers", composersService.findAll());
        model.addAttribute("essays", essaysService.getAllEssay());
        model.addAttribute("composerCounter", new AtomicInteger(0));
        model.addAttribute("essayCounter", new AtomicInteger(0));
        return "account/adminPanel";
    }

    @GetMapping("/guest")
    public String showGuest() {
        return "account/guest";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }

}
