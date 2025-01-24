package com.example.researchproject.infrastructure.adapters.input.auth;
import com.example.researchproject.application.ports.dto.UserDTO;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.application.services.UserService;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;


    public AuthController(AuthenticationManager authenticationManager, UserService userService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    //register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> userPayload) {
        String username = (String) userPayload.get("username");
        String password = (String) userPayload.get("password");
        String email = (String) userPayload.get("email");

        //validatie


        // Ensure roles exist and are correctly parsed
        Object assignedRole = userPayload.get("roles");
        List<String> roles = new ArrayList<>();

        if (assignedRole instanceof List<?>) {
            roles = ((List<?>) assignedRole).stream()
                    .map(Object::toString) // Ensure it's a String
                    .toList();
        }
        try {
            // nieuwe methode met DTO
            UserDTO createdUserDTO = userService.register(username, password, email, roles);
            return ResponseEntity.ok(createdUserDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        //userService.register(username, password, email, roles);
        //return ResponseEntity.ok("Register successful");



    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginPayload) {
        String username = loginPayload.get("username");
        String password = loginPayload.get("password");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(Map.of(
                "username", username,
                "email", userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")).getEmail()
        ));
    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Logout successful");
    }






}
