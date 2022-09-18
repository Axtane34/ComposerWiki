package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Chorus;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ChoirsService;
import ru.axtane.CAHI.services.DraftsService;

@Controller
@RequestMapping("/chorus")
public class ChorusController {
    private final ChoirsService choirsService;
    private final DraftsService draftsService;

    @Autowired
    public ChorusController(ChoirsService choirsService, DraftsService draftsService) {
        this.choirsService = choirsService;
        this.draftsService = draftsService;
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
    public String newChorus(@ModelAttribute("chorus") Chorus chorus, Model model){
        model.addAttribute("draft", new Draft());
        return "chorus/newChorus";
    }

    @GetMapping("/newChorus/{id}")
    public String newChorusFromDraft(@ModelAttribute("chorus") Chorus chorus, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "chorus/newChorus";
    }

    @PostMapping()
    public String create(@ModelAttribute("chorus") Chorus chorus){
        chorus.setUserAuthor(getPerson());
        choirsService.save(chorus);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}