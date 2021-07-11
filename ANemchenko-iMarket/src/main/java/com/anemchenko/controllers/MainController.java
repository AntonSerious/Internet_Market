package com.anemchenko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/nav")
    public String showNavigation(){
        return "nav";
    }
}
