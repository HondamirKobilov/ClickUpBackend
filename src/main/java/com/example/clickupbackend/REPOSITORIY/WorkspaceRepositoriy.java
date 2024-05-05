package com.example.clickupbackend.REPOSITORIY;
import com.example.clickupbackend.ENTITIY.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface WorkspaceRepositoriy extends JpaRepository<Workspace, Long> {
    boolean existsByNomiAndUsersId(String nomi, UUID users_id);


}
