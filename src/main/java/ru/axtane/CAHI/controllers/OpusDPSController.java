package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.OpusDPS;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.DraftsService;
import ru.axtane.CAHI.services.OpusDPSService;
import ru.axtane.CAHI.services.PeopleService;

@Controller
@RequestMapping("/opusDPS")
public class OpusDPSController {
    private final OpusDPSService opusDPSService;
    private final DraftsService draftsService;

    @Autowired
    public OpusDPSController(OpusDPSService opusDPSService, DraftsService draftsService) {
        this.opusDPSService = opusDPSService;
        this.draftsService = draftsService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("opusDPS", opusDPSService.findById(id));
        return "opusDPS/aboutOpusDPS";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        opusDPSService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newOpusDPS")
    public String newOpusDPS(@ModelAttribute("opusDPS") OpusDPS opusDPS, Model model){
        model.addAttribute("draft", new Draft());
        return "opusDPS/newOpusDPS";
    }

    @GetMapping("/newOpusDPS/{id}")
    public String newOpusDPSFromDraft(@ModelAttribute("opusDPS") OpusDPS opusDPS, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "opusDPS/newOpusDPS";
    }

    @PostMapping()
    public String create(@ModelAttribute("opusDPS") OpusDPS opusDPS){
        opusDPS.setUserAuthor(getPerson());
        opusDPSService.save(opusDPS);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
