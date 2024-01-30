package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.converters.UserConverter;
import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.user_exceptions.EmailNotFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.UserNotFoundException;
import com.mindera.HelloMam.repositories.UserRepository;
import com.mindera.HelloMam.securities.SecurityConfig;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("userId")
public class HomeController {

    private UserRepository userRepository;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public HomeController(UserRepository userRepository, UserServiceImpl userServiceImpl) {
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
    }

        @GetMapping("/")
        public String home(){
            return "home";
        }

    @GetMapping("/addinterests")
    public String secured(@SessionAttribute("userId") Long userId, Model model) throws UserNotFoundException {
        // Use the userId to fetch user data from the database
        User user = userServiceImpl.findById(userId);

        // Add the user data to the model
        model.addAttribute("user", user);

        // Return the name of the view
        return "addinterests";
    }

    @PostMapping("/addinterests")
    public String addInterests(@RequestParam("interests[]") List<String> interestList, @SessionAttribute("userId") Long userId) throws UserNotFoundException, EmailNotFoundException {
        User user = userServiceImpl.findById(userId);
        userServiceImpl.updateInterests(user.getId(), interestList);
        return "addinterests";
    }

        @GetMapping("/register")
        public String register(){
            return "register";
        }

    @GetMapping("/updateuser")
    public String start(Authentication authentication, Model model) throws UserNotFoundException {
        Object principal = authentication.getPrincipal();
        User user = null;
        String email = null;
        String name = null;

        if (principal instanceof OidcUser) {
            OidcUser oidcUser = (OidcUser) principal;
            email = oidcUser.getEmail();
            name = oidcUser.getFullName(); // get the name from the OidcUser
        } else if (principal instanceof DefaultOAuth2User) {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) principal;
            Map<String, Object> attributes = defaultOAuth2User.getAttributes();
            email = (String) attributes.get("email");
            name = (String) attributes.get("name"); // get the name from the attributes
        }

        if (email != null) {
            Optional<UserGetDto> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                user = userServiceImpl.findById(optionalUser.get().id());
            } else {
                user = new User();
                user.setEmail(email);
                user.setName(name); // set the name when creating a new User
                userRepository.save(user);
            }
        }

        if (user != null) {
            user.setActive(true);
            model.addAttribute("user", user);
            System.out.println("User: " + user.getName() + " " + user.getEmail());
        }

        return "updateuser";
    }

        @PostMapping("/updateuser")
        public String updateUser(@RequestBody UserCreateDto userCreateDto) throws UserNotFoundException, EmailNotFoundException {
            UserGetDto userGetDto = userServiceImpl.findByEmail(userCreateDto.email());
            userServiceImpl.updateUser(userGetDto.id(), userCreateDto);
            return "redirect:/profile";
        }

        @GetMapping("/logout")
        public String logout(){
            return "redirect:/";
        }

        @GetMapping("/profile")
        public String profile(Authentication authentication, Model model, HttpServletRequest request) throws UserNotFoundException {
            Object principal = authentication.getPrincipal();
            User user = null;
            String email = null;

            if (principal instanceof OidcUser) {
                OidcUser oidcUser = (OidcUser) principal;
                email = oidcUser.getEmail();
            } else if (principal instanceof DefaultOAuth2User) {
                DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) principal;
                Map<String, Object> attributes = defaultOAuth2User.getAttributes();
                email = (String) attributes.get("email");
            }

            if (email != null) {
                Optional<UserGetDto> optionalUser = userRepository.findByEmail(email);
                if (optionalUser.isPresent()) {
                    user = userServiceImpl.findById(optionalUser.get().id());
                } else {
                    user = new User();
                    user.setEmail(email);
                    userRepository.save(user);
                }
            }

            if (user != null) {
                model.addAttribute("name", user.getName());
                model.addAttribute("email", user.getEmail());
                model.addAttribute("userId", user.getId());
                model.addAttribute("user", user);
                System.out.println("User: " + user.getName() + " " + user.getEmail());
            }

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());

            return "profile";
        }

        @GetMapping("/login")
        public String login(){
        return "redirect:/profile";
        }
}