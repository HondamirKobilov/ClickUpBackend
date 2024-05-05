package com.example.clickupbackend.REPOSITORIY;

import com.example.clickupbackend.ENTITIY.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepositoriy extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndEmailKod(String username, String emailKod);

    boolean existsByUsername(String username);
}
