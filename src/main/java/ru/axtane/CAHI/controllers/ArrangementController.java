package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Arrangement;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ArrangementsService;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.DraftsService;

@Controller
@RequestMapping("/arrangement")
public class ArrangementController {
    private final ArrangementsService arrangementsService;
    private final DraftsService draftsService;
    private final ComposersService composersService;

    @Autowired
    public ArrangementController(ArrangementsService arrangementsService, DraftsService draftsService, ComposersService composersService) {
        this.arrangementsService = arrangementsService;
        this.draftsService = draftsService;
        this.composersService = composersService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("arrangement", arrangementsService.findById(id));
        return "arrangement/aboutArrangement";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        arrangementsService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newArrangement")
    public String newArrangement(@ModelAttribute("arrangement") Arrangement arrangement, Model model,
                                 @ModelAttribute("composer")Composer composer){
        model.addAttribute("draft", new Draft());
        model.addAttribute("composers", composersService.findAll());
        return "arrangement/newArrangement";
    }

    @GetMapping("/newArrangement/{id}")
    public String newArrangementFromDraft(@ModelAttribute("arrangement") Arrangement arrangement, Model model,
                                          @ModelAttribute("composer") Composer composer, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        model.addAttribute("composers", composersService.findAll());
        return "arrangement/newArrangement";
    }

    @PostMapping()
    public String create(@ModelAttribute("arrangement") Arrangement arrangement, @ModelAttribute("composer") Composer composer){
        arrangement.setUserAuthor(getPerson());
        if (!"".equals(composer.getFio())){
            arrangement.setComposer(composersService.findByFio(composer.getFio()));
        }
        arrangementsService.save(arrangement);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}