package com.example.clickup.repository;

import com.example.clickup.entitiy.WorkSpaceUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkSpaceUsersRepository extends JpaRepository<WorkSpaceUsers, Long> {
    boolean existsByUsersIdAndWorkSpaceId(UUID users_id, Long workSpace_id);
}
