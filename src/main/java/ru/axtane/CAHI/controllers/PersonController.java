package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.PeopleService;
import ru.axtane.CAHI.services.RegistrationService;
import ru.axtane.CAHI.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/CAHI")
public class PersonController {
    private final PeopleService peopleService;
    private final ComposersService composersService;
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PeopleService peopleService, ComposersService composersService, RegistrationService registrationService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.composersService = composersService;
        this.registrationService = registrationService;
        this.personValidator = personValidator;
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

    @GetMapping("/account")
    public String show(Model person) {
        //registration.addAttribute("drafts", registrationsService.findAllWithEnum(PublicationStatus.DRAFT, login));
        //registration.addAttribute("moderating", registrationsService.findAllWithEnum(PublicationStatus.MODERATING, login));
        person.addAttribute("publications", peopleService.findAllWithEnum(PublicationStatus.PUBLISHED, getPerson().getUsername()));
        person.addAttribute("composers", composersService.findAll());
        return "account/user";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
