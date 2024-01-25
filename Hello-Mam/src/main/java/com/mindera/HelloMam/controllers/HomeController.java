package com.mindera.HelloMam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

        @GetMapping("/")
        public String home(){
            return "home";
        }

        @GetMapping("/register")
        public String register(){
            return "register";
        }

        @PostMapping("/register")
        public String registerPost(){
            return "register";
        }
}
