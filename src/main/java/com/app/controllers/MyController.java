package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/user")
    public String testUser() {
        return "testUser";
    }

    @GetMapping("/admin")
    public String testAdmin() {
        return "testAdmin";
    }

    @GetMapping("/super")
    public String testSuper() {
        return "testSuper";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("error", "");
        return "loginForm";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/login/error")
    public String loginError(Model model)
    {
        final String loginErrorMessage = "Nieprawidłowe dane logowania";
        model.addAttribute("error", loginErrorMessage);
        return "loginForm";
    }
}
