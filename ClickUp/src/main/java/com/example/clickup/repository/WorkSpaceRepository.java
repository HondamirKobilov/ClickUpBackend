package com.example.clickup.repository;

import com.example.clickup.entitiy.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {
    boolean existsByNomiAndUsersId(String nomi, UUID users_id);
}
