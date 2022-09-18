package ru.axtane.CAHI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.security.PersonDetails;
import ru.axtane.CAHI.services.DraftsService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/draft")
public class DraftController {
    private final DraftsService draftsService;

    @Autowired
    public DraftController(DraftsService draftsService) {
        this.draftsService = draftsService;
    }

    @PostMapping("/newDraft")
    public String create(@ModelAttribute("draft") Draft draft, HttpServletRequest httpServletRequest){
        draft.setDraftType(draftsService.setType(httpServletRequest.getHeader("referer")));
        draft.setUserAuthor(getPerson());
        draftsService.save(draft);
        return "redirect:/CAHI/account";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("updatedDraft") Draft updatedDraft, @PathVariable("id") int id){
        draftsService.update(id, updatedDraft);
        return "redirect:/CAHI/account";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        draftsService.delete(id);
        return "redirect:/CAHI/account";
    }

    private Person getPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return personDetails.getPerson();
    }


}
