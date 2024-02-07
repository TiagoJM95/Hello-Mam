package com.mindera.HelloMam.controllers;

import org.springframework.ui.Model;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.services.implementations.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    private AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final JwtDecoder jwtDecoder;

    public HomeController(TokenService tokenService, AuthenticationManager authenticationManager, JwtDecoder jwtDecoder) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // getters and setters

    }

    @Getter
    @Setter
    public static class TokenResponse {
        private String token;

        // getters and setters
    }

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private Authentication authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @PostMapping("/login")
    public String loginPost(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        // Extract username and password from the request body
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        LOG.info("Logging in user {}", username);
        LOG.info("Logging in with password {}", password);


        // Create an authentication token using the provided username and password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            LOG.info("Authentication {}", authentication);
            // Generate a token for the authenticated user
            String token = tokenService.generateToken(authentication);
            LOG.info("Token {}", token);

            // Set the token in the Authorization header of the response
            response.setHeader("Authorization", "Bearer " + token);
            response.setContentType("application/json");
            //getUsernameFromToken(token, jwtDecoder);
            LOG.info("Username from authentication {}", authentication.getName());

            return "redirect:/profile";
        } catch (AuthenticationException e) {

            return "login";
        }
    }

    public String getUsernameFromToken(String token, JwtDecoder jwtDecoder) {
        // Decode the token
        Jwt jwt = jwtDecoder.decode(token);

        // Get the claims
        Map<String, Object> claims = jwt.getClaims();

        // Get the username from the claims
        String username = (String) claims.get("sub"); // 'sub' is the standard claim for the subject (usually the username)

        return username;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        LOG.info("Authorization header: " + authorizationHeader);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        LOG.info("Username: " + username);
        model.addAttribute("username", username);
        return "profile";
    }



}
