/*
package com.mindera.HelloMam.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile("test")
public class SecurityConfigTest {

   @Bean
   @Primary
    SecurityFilterChain TestSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Security Config Test Accessed");
        http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeRequests().anyRequest().permitAll();
        return http.build();
    }
}
*/
