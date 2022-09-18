package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Arrangement;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ArrangementsService;
import ru.axtane.CAHI.services.DraftsService;

@Controller
@RequestMapping("/arrangement")
public class ArrangementController {
    private final ArrangementsService arrangementsService;
    private final DraftsService draftsService;

    @Autowired
    public ArrangementController(ArrangementsService arrangementsService, DraftsService draftsService) {
        this.arrangementsService = arrangementsService;
        this.draftsService = draftsService;
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
    public String newArrangement(@ModelAttribute("arrangement") Arrangement arrangement, Model model){
        model.addAttribute("draft", new Draft());
        return "arrangement/newArrangement";
    }

    @GetMapping("/newArrangement/{id}")
    public String newArrangementFromDraft(@ModelAttribute("arrangement") Arrangement arrangement, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "arrangement/newArrangement";
    }

    @PostMapping()
    public String create(@ModelAttribute("arrangement") Arrangement arrangement){
        arrangement.setUserAuthor(getPerson());
        arrangementsService.save(arrangement);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}