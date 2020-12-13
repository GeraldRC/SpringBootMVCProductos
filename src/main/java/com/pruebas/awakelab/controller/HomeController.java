package com.pruebas.awakelab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model){
        model.addAttribute("titulo","Home");
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("titulo", "Admin" );
        return "admin/homeAdmin";
    }


}
