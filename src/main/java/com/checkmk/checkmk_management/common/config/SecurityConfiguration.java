package com.checkmk.checkmk_management.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder createEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //For password hashing
        return encoder;
    }

    @Bean
    public SecurityFilterChain securityFilters(HttpSecurity http) throws Exception{
        String[] allowedPaths = {"/login", "/register", "/h2-console/**", "/**"};
        String[] allowedResources = {"/css/**", "/images/**", "/js/**"};

        //permitAll = everyone has permission
        //authenticated = logged user
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(allowedPaths)
                                .permitAll()
                                .requestMatchers(allowedResources)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(login -> login
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/menu")
                                .permitAll()
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                )
                .headers(headers -> headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                                .contentSecurityPolicy(csp -> csp
                                    .policyDirectives(
                                        "script-src 'self' 'unsafe-inline' 'unsafe-eval' https://cdn.jsdelivr.net; " +
                                        "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://cdn.lineicons.com; " +
                                        "font-src 'self' https://cdn.jsdelivr.net https://cdn.lineicons.com; " +
                                        "img-src 'self' data: https:; " +
                                        "connect-src 'self'; " +
                                        "frame-ancestors 'self'; " +
                                        "form-action 'self'; " +
                                        "object-src 'none'"
                                    ))
                ); 

        return http.build();
    }



    
}
