/*
package com.mindera.HelloMam.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfig {

   @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       System.out.println("Config Accessed");
        return http
               .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/api/v1/user/redistest/").permitAll();
                    auth.anyRequest().authenticated();
               })
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/profile", true))
                .logout(logout -> logout
                        .logoutUrl("/logout") // specify the logout URL
                        .logoutSuccessUrl("/") // specify the logout success URL
                )
                .build();
    }
}
*/
