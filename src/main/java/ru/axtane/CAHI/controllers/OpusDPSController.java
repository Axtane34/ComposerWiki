package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.OpusDPS;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.DraftsService;
import ru.axtane.CAHI.services.OpusDPSService;

@Controller
@RequestMapping("/opusDPS")
public class OpusDPSController {
    private final OpusDPSService opusDPSService;
    private final DraftsService draftsService;
    private final ComposersService composersService;

    @Autowired
    public OpusDPSController(OpusDPSService opusDPSService, DraftsService draftsService, ComposersService composersService) {
        this.opusDPSService = opusDPSService;
        this.draftsService = draftsService;
        this.composersService = composersService;
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
    public String newOpusDPS(@ModelAttribute("opusDPS") OpusDPS opusDPS, Model model,
                             @ModelAttribute("composer") Composer composer){
        model.addAttribute("draft", new Draft());
        model.addAttribute("composers", composersService.findAll());
        return "opusDPS/newOpusDPS";
    }

    @GetMapping("/newOpusDPS/{id}")
    public String newOpusDPSFromDraft(@ModelAttribute("opusDPS") OpusDPS opusDPS, Model model,
                                      @ModelAttribute("composer")Composer composer, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        model.addAttribute("composers", composersService.findAll());
        return "opusDPS/newOpusDPS";
    }

    @PostMapping()
    public String create(@ModelAttribute("opusDPS") OpusDPS opusDPS, @ModelAttribute("composer")Composer composer){
        opusDPS.setUserAuthor(getPerson());
        if (!"".equals(composer.getFio())){
            opusDPS.setComposer(composersService.findByFio(composer.getFio()));
        }
        opusDPSService.save(opusDPS);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
