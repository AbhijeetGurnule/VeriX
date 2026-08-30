package com.project.auth_app.repositories;

import com.project.auth_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Custom Find Method
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
