package com.mindera.HelloMam.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

        @RequestMapping("/")
        public String home(){
            return "Hello Mam!";
        }
}
