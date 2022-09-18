package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.FolkProcessing;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.DraftsService;
import ru.axtane.CAHI.services.FolkProcessingService;
import ru.axtane.CAHI.services.PeopleService;

@Controller
@RequestMapping("/folkProcessing")
public class FolkProcessingController {
    private final FolkProcessingService folkProcessingService;
    private final DraftsService draftsService;

    @Autowired
    public FolkProcessingController(FolkProcessingService folkProcessingService, DraftsService draftsService) {
        this.folkProcessingService = folkProcessingService;
        this.draftsService = draftsService;
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
    public String newFolkProcessing(@ModelAttribute("folkProcessing") FolkProcessing folkProcessing, Model model){
        model.addAttribute("draft", new Draft());
        return "folkProcessing/newFolkProcessing";
    }

    @GetMapping("/newFolkProcessing/{id}")
    public String newFolkProcessingFromDraft(@ModelAttribute("folkProcessing") FolkProcessing folkProcessing, Model model, @PathVariable("id") int id){
        model.addAttribute("updatedDraft", draftsService.findById(id));
        return "folkProcessing/newFolkProcessing";
    }

    @PostMapping()
    public String create(@ModelAttribute("folkProcessing") FolkProcessing folkProcessing){
        folkProcessing.setUserAuthor(getPerson());
        folkProcessingService.save(folkProcessing);
        return "redirect:/CAHI/{account}";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
