package com.example.grooming_booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("password123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Публичные страницы и API
                        .requestMatchers(
                                "/login.html",
                                "/",
                                "/index.html",
                                "/services",
                                "/appointments",
                                "/appointments/available"
                        ).permitAll()

                        .requestMatchers(
                                "/admin.html",
                                "/appointments/all",
                                "/appointments/*/complete"
                        ).authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/appointments/**").authenticated()

                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/admin.html", true)
                        .failureUrl("/login.html?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html")
                );

        return http.build();
    }
}

