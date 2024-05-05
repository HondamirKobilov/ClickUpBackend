package com.example.clickup.repository;

import com.example.clickup.entitiy.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Users> findByUsernameAndKod(String username, String emailKod);
}
