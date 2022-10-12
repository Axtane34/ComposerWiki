package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.DraftsService;

@Controller
@RequestMapping("/composer")
public class ComposerController {
    private final ComposersService composersService;
    private final DraftsService draftsService;

    @Autowired
    public ComposerController(ComposersService composersService, DraftsService draftsService) {
        this.composersService = composersService;
        this.draftsService = draftsService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Composer composer = composersService.findById(id);
            model.addAttribute("composer", composer);
            model.addAttribute("cappellaChoirs", composersService.findChoirs(PublicationStatus.PUBLISHED, composer, true));
            model.addAttribute("instrumentalChoirs", composersService.findChoirs(PublicationStatus.PUBLISHED, composer, false));
        return "composer/aboutComposer";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        composersService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newComposer")
    public String newComposer(@ModelAttribute("composer") Composer composer, Model model){
        model.addAttribute("draft", new Draft());
        return "composer/newComposer";
    }

    @GetMapping("/newComposer/{id}")
    public String newComposerFromDraft(@ModelAttribute("composer") Composer composer, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "composer/newComposer";
    }

    @PostMapping()
    public String create(@ModelAttribute("composer") Composer composer){
        composer.setUserAuthor(getPerson());
        composersService.save(composer);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}