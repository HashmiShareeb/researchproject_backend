package com.example.researchproject.infrastructure.adapter.web;

import com.example.researchproject.application.ports.dto.AssignRoleDTO;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.domain.exceptions.RoleNotFoundException;
import com.example.researchproject.domain.models.User.User;
import com.example.researchproject.domain.models.User.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
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
    public ResponseEntity<?> assignRole(@RequestBody AssignRoleDTO assignRoleDTO) {
        String username = assignRoleDTO.getUsername();
        List<String> roles = assignRoleDTO.getRoles();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (roles == null || roles.isEmpty()) {
            throw new RoleNotFoundException("Roles cannot be empty");
        }

        // Toegestane rollen definiëren
        List<String> allowedRoles = List.of("USER", "ADMIN", "OPERATOR");

        for (String role : roles) {
            if (!allowedRoles.contains(role.toUpperCase())) {
                throw new RoleNotFoundException("Invalid role: " + role);
            }
        }

        // Zet nieuwe rollen en sla op
        user.setRoles(new ArrayList<>(roles.stream().map(role -> Role.valueOf(role.toUpperCase())).toList()));
        userRepository.save(user);

        return ResponseEntity.ok("Role assigned to user " + username);
    }







}
