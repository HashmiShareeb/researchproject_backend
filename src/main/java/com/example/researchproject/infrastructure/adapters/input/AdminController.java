package com.example.researchproject.infrastructure.adapters.input;

import com.example.researchproject.application.ports.dto.AssignRoleDTO;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.domain.exceptions.RoleNotFoundException;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //asign roles
    @PutMapping("/assign-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> assignRole(@RequestBody AssignRoleDTO assignRoleDTO, Authentication authentication) {

        //log user and their assigned role
        System.out.println("Authenticated user: " + authentication.getName());
        System.out.println("User roles: " + authentication.getAuthorities());

        String username = assignRoleDTO.getUsername();
        List<String> roles = assignRoleDTO.getRoles();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(roles.isEmpty()){
            throw new RoleNotFoundException("Roles cannot be empty");
        }

        for (String role : roles) {
            if (!List.of("USER", "ADMIN", "OPERATOR").contains(role.toUpperCase())) {
                throw new RoleNotFoundException("Role not found: " + role);
            }
        }

        user.setRoles(roles.stream().map(Role::valueOf).toList());
        userRepository.save(user);
        return ResponseEntity.ok("Role changed");
    }


    @GetMapping("/test-auth")
    public ResponseEntity<?> testAuth(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }
        return ResponseEntity.ok("Logged in as: " + authentication.getName() + " with roles " + authentication.getAuthorities());
    }


}
