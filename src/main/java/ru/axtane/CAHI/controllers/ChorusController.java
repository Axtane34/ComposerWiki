package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Chorus;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ChoirsService;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.DraftsService;

@Controller
@RequestMapping("/chorus")
public class ChorusController {
    private final ChoirsService choirsService;
    private final DraftsService draftsService;
    private final ComposersService composersService;

    @Autowired
    public ChorusController(ChoirsService choirsService, DraftsService draftsService, ComposersService composersService) {
        this.choirsService = choirsService;
        this.draftsService = draftsService;
        this.composersService = composersService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("chorus", choirsService.findById(id));
        return "chorus/aboutChorus";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        choirsService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newChorus")
    public String newChorus(@ModelAttribute("chorus") Chorus chorus, Model model,
                            @ModelAttribute("composer") Composer composer){
        model.addAttribute("draft", new Draft());
        model.addAttribute("composers", composersService.findAll());
        return "chorus/newChorus";
    }

    @GetMapping("/newChorus/{id}")
    public String newChorusFromDraft(@ModelAttribute("chorus") Chorus chorus, Model model,
                                     @ModelAttribute("composer") Composer composer, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        model.addAttribute("composers", composersService.findAll());
        return "chorus/newChorus";
    }

    @PostMapping()
    public String create(@ModelAttribute("chorus") Chorus chorus, @ModelAttribute("composer") Composer composer){
        chorus.setUserAuthor(getPerson());
        if (!"".equals(composer.getFio())){
            chorus.setComposer(composersService.findByFio(composer.getFio()));
        }
        choirsService.save(chorus);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}