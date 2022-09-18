package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.axtane.CAHI.models.Chants;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ChantsService;
import ru.axtane.CAHI.services.DraftsService;
import ru.axtane.CAHI.services.PeopleService;

@Controller
@RequestMapping("/chants")
public class ChantsController {
    private final ChantsService chantsService;
    private final DraftsService draftsService;

    @Autowired
    public ChantsController(ChantsService chantsService, DraftsService draftsService) {
        this.chantsService = chantsService;
        this.draftsService = draftsService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("chants", chantsService.findById(id));
        return "chants/aboutChants";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        chantsService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newChants")
    public String newChants(@ModelAttribute("chants") Chants chants, Model model){
        model.addAttribute("draft", new Draft());
        return "chants/newChants";
    }

    @GetMapping("/newChants/{id}")
    public String newChantsFromDraft(@ModelAttribute("chants") Chants chants, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "chants/newChants";
    }

    @PostMapping()
    public String create(@ModelAttribute("chants") Chants chants){
        chants.setUserAuthor(getPerson());
        chantsService.save(chants);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}