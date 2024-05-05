package com.example.clickup.repository;

import com.example.clickup.entitiy.Reklama;
import com.example.clickup.entitiy.RezervUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RezervUserRepository extends JpaRepository<RezervUser, Long> {
}
