package ru.zhigalin.restapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @GetMapping("/admin")
    public String getRest() {
        return "rest1";
    }

    @GetMapping("/user")
    public String getCurrentUser() {
        return "rest2";
    }
}
