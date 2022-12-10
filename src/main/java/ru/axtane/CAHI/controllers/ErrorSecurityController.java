package ru.axtane.CAHI.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorSecurityController implements ErrorController {

    @GetMapping("/error")
    public String getErrorMessage(){
        return "account/error";
    }

    @PostMapping("/error")
    public String postErrorMessage(){
        return "account/error";
    }

    @PatchMapping("/error")
    public String patchErrorMessage(){
        return "account/error";
    }

    @DeleteMapping("/error")
    public String deleteErrorMessage(){
        return "account/error";
    }
}
