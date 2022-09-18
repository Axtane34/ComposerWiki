package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.OpusAS;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.DraftsService;
import ru.axtane.CAHI.services.OpusASService;
import ru.axtane.CAHI.services.PeopleService;

@Controller
@RequestMapping("/opusAS")
public class OpusASController {
    private final OpusASService opusASService;
    private final DraftsService draftsService;

    @Autowired
    public OpusASController(OpusASService opusASService, DraftsService draftsService) {
        this.opusASService = opusASService;
        this.draftsService = draftsService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("opusAS", opusASService.findById(id));
        return "opusAS/aboutOpusAS";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        opusASService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newOpusAS")
    public String newOpusAS(@ModelAttribute("opusAS") OpusAS opusAS, Model model){
        model.addAttribute("draft", new Draft());
        return "opusAS/newOpusAS";
    }

    @GetMapping("/newOpusAS/{id}")
    public String newOpusASFromDraft(@ModelAttribute("opusAS") OpusAS opusAS, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "opusAS/newOpusAS";
    }

    @PostMapping()
    public String create(@ModelAttribute("opusAS") OpusAS opusAS){
        opusAS.setUserAuthor(getPerson());
        opusASService.save(opusAS);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}