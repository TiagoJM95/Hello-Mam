package com.mindera.HelloMam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
