package com.example.SpringSecurityDemo.WEB;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/users/login")

    public String login() {
        return "auth-login";
    }

}
