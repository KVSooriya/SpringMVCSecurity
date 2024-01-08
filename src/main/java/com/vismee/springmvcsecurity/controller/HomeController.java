package com.vismee.springmvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String showHome()
    {
        return "home";
    }

    @GetMapping("/courseMaterials")
    public String getMaterials()
    {
        return "course-material";
    }

    @GetMapping("/courseFees")
    public String getFees()
    {
        return "course-fee";
    }

}
