package com.example.researchproject.application.services;

import com.example.researchproject.application.ports.dto.UserDTO;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.domain.exceptions.RoleNotFoundException;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())) // Spring expects "ROLE_"
                        .collect(Collectors.toList())
        );
    }

    /*public boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username);
    } */

    //register user
    /*public User register(String username, String password, String email, List<String> roles) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        //role assignments
        // Convert and validate roles
        List<Role> userRoles = validateAndConvertRoles(roles);

        //pw hasher
        String encodedPassword = new BCryptPasswordEncoder().encode(password);


        User user = new User(username, encodedPassword, email, userRoles);
        return userRepository.save(user);
    } */

    //toon users gerigistreerde users in DTO (wachtwoorden en gevoelige data niet tonen)
    public UserDTO register(String username, String password, String email, List<String> roles) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameNotFoundException("Username already exists: " + username);
        }

        //zie methode hieronder
        List<Role> userRoles = validateAndConvertRoles(roles);

        // encryptie
        String encodedPassword = new BCryptPasswordEncoder().encode(password);


        User user = new User(username, encodedPassword, email, userRoles);


        User savedUser = userRepository.save(user);


        // Map the saved user entity to UserDTO
        return new UserDTO(
                savedUser.getUserId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRoles().stream().map(Role::name).collect(Collectors.toList())
        );
    }


    // Helper method to validate and convert roles from String to Role enum
    private List<Role> validateAndConvertRoles(List<String> roles) {
        return roles.stream()
                .map(role -> {
                    try {
                        return Role.valueOf(role.toUpperCase());
                    } catch (RoleNotFoundException e) {
                        throw new RoleNotFoundException("Invalid role: " + role);
                    }
                })
                .collect(Collectors.toList());
    }



}