package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.FolkProcessing;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.ComposersService;
import ru.axtane.CAHI.services.DraftsService;
import ru.axtane.CAHI.services.FolkProcessingService;

@Controller
@RequestMapping("/folkProcessing")
public class FolkProcessingController {
    private final FolkProcessingService folkProcessingService;
    private final DraftsService draftsService;
    private final ComposersService composersService;

    @Autowired
    public FolkProcessingController(FolkProcessingService folkProcessingService, DraftsService draftsService, ComposersService composersService) {
        this.folkProcessingService = folkProcessingService;
        this.draftsService = draftsService;
        this.composersService = composersService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("folkProcessing", folkProcessingService.findById(id));
        return "folkProcessing/aboutFolkProcessing";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        folkProcessingService.delete(id);
        return "redirect:/CAHI/account";
    }

    @GetMapping("/newFolkProcessing")
    public String newFolkProcessing(@ModelAttribute("folkProcessing") FolkProcessing folkProcessing, Model model,
                                    @ModelAttribute("composer") Composer composer){
        model.addAttribute("draft", new Draft());
        model.addAttribute("composers", composersService.findAll());
        return "folkProcessing/newFolkProcessing";
    }

    @GetMapping("/newFolkProcessing/{id}")
    public String newFolkProcessingFromDraft(@ModelAttribute("folkProcessing") FolkProcessing folkProcessing, Model model,
                                             @ModelAttribute("composer") Composer composer, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        model.addAttribute("composers", composersService.findAll());
        return "folkProcessing/newFolkProcessing";
    }

    @PostMapping()
    public String create(@ModelAttribute("folkProcessing") FolkProcessing folkProcessing, @ModelAttribute("composer") Composer composer){
        folkProcessing.setUserAuthor(getPerson());
        if (!"".equals(composer.getFio())){
            folkProcessing.setComposer(composersService.findByFio(composer.getFio()));
        }
        folkProcessingService.save(folkProcessing);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
