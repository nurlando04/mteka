package com.example.tempHumProject.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository {
    Optional<User> findByUsername(String login);
    Boolean existsByUsername(String login);
    Boolean existsByEmail(String email);
}
