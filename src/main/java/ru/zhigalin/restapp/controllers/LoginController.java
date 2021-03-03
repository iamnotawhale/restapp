package ru.zhigalin.restapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping
    public String index() {
        return "loginPage";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "loginPage";
    }
}
