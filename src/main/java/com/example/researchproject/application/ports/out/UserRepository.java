package com.example.researchproject.application.ports.out;

import com.example.researchproject.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //boolean existsByEmail(String email);

    //User findByEmail(String email);

    Optional<User> findByUsername(String username);


}
