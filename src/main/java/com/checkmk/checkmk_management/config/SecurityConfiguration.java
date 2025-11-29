package com.checkmk.checkmk_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder createEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public SecurityFilterChain securityFilters(HttpSecurity http) throws Exception{
        String[] allowedPaths = {"/login", "/register", "/h2-console/**","/**"};
        String[] allowedResources = {"/css/**", "/images/**", "/js/**"};

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(allowedPaths).permitAll()
            .requestMatchers(allowedResources).permitAll().anyRequest().authenticated()
        )
        .formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login").permitAll());

        return http.build();
    }



    
}
