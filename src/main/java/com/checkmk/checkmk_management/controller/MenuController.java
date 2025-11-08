package com.checkmk.checkmk_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MenuController {

    @GetMapping("/menu")
    public String showMenu() {
        return "menu";
    }
    
    
}
