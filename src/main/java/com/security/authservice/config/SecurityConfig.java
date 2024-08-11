package com.security.authservice.config;

import com.security.authservice.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home","/login","/auth/register","/auth/validate/**").permitAll()
                        .anyRequest().authenticated())
              .httpBasic(Customizer.withDefaults());

        return http.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
      *//*  UserDetails user =
                User.builder()
                        .username("user12")
                        .password(passwordEncoder().encode("pass123"))
                        .roles("USER")
                        .build();

          return new InMemoryUserDetailsManager(user);
       *//*
        return new CustomUserDetailService();
    }*/

}
