package com.example.researchproject.infrastructure.adapters.config;

import com.example.researchproject.application.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class RestClientConfig {
    private final UserService userService;
    
    
    //https://www.baeldung.com/role-and-privilege-for-spring-security-registration
    public RestClientConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //Finally, add  the expressionHandler into http.authorizeRequests():
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Allow registration and login publicly
                        .requestMatchers("/api/rides/**").permitAll() // Allow public access to rides (adjust if necessary)
                        .requestMatchers("/**").permitAll() // Temporarily allow all requests
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict admin endpoints
                        .anyRequest().authenticated()  // Any other request requires authentication
                )
                .formLogin(form -> form
                        .loginPage("/api/auth/login") // Custom login endpoint
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/api/auth/logout")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless authentication (JWT)
                .httpBasic(AbstractHttpConfigurer::disable) // Disable HTTP Basic Auth
                .formLogin(AbstractHttpConfigurer::disable); // Disable form login
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use bcrypt for password hashing
    }



}
