package com.example.researchproject.infrastructure.adapters;

import com.example.researchproject.application.ports.dto.AssignRoleDTO;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.domain.exceptions.RoleNotFoundException;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //asign roles
    @PostMapping("/assign-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN')") //enkel voor admin
    public ResponseEntity<?> assignRole(@RequestBody AssignRoleDTO assignRoleDTO) {
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

}
