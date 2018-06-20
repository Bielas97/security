package com.app.controllers;

import com.app.model.Role;
import com.app.model.dto.UserDto;
import com.app.service.UserService;
import com.app.validators.UserValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class RegisterController {

    private UserService userService;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterController(UserService userService/*, BCryptPasswordEncoder bCryptPasswordEncoder*/) {
        this.userService = userService;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @InitBinder
    private void initializeBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserValidator());
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("roles", Role.values());
        model.addAttribute("errors", new HashMap<>());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(
            @Valid @ModelAttribute UserDto userDto,
            BindingResult bindingResult,
            Model model
    ){

        if (bindingResult.hasErrors()) {
            Map<String, String> errors
                    = bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));
            model.addAttribute("errors", errors);
            model.addAttribute("userDto", userDto);
            model.addAttribute("roles", Role.values());
            return "register";
        }

        // userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.addOrUpdateUser(userDto);
        return "redirect:/";
    }

}
