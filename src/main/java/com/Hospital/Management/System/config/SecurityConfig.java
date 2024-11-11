package com.Hospital.Management.System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity; evaluate enabling in production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Restrict /api/admin/** to ADMIN role
                .requestMatchers("/api/doctor/**").hasRole("DOCTOR") // Restrict /api/doctor/** to DOCTOR role
                .requestMatchers("/api/patient/**").hasRole("PATIENT") // Restrict /api/patient/** to PATIENT role
                .anyRequest().authenticated() // Require authentication for all other requests
            )
            .formLogin(form -> form // Configure form login
                .loginPage("/login") // Custom login page (replace "/login" with your login page URL)
                .permitAll() // Allow everyone to see the login page
                .defaultSuccessUrl("/home", true) // Redirect to /home on successful login
            )
            .httpBasic(httpBasic -> {}); // Enable HTTP Basic authentication

        return http.build();
    }
}
