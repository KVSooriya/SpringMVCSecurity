package com.vismee.springmvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
    @GetMapping("/showLogin")
    public String showLogin()
    {
        return "login-prettypage";
    }

    @GetMapping("/accessDenied")
    public String showAccessDenied()
    {
        return "access-denied";
    }
}
