package com.MikeKtr.KindleSorter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.MikeKtr.KindleSorter.service.UserService;

@Configuration
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
            ).csrf(AbstractHttpConfigurer::disable).formLogin(withDefaults());
    
        return http.build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http
            .getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder)
            .and()
            .build();
    }


    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    //     http
    //         .authorizeHttpRequests((authorize) -> authorize
    //             .requestMatchers("/login.html", "/css/**", "/js/**", "/images/**").permitAll()
    //             .anyRequest().authenticated());
    //         // .formLogin(form -> form
    //         //     .loginPage("/login.html")
    //         //     .permitAll());
    //                     return http.build();
            
            
            
    // }

}

