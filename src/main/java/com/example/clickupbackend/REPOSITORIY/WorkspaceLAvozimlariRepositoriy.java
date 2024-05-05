package com.example.clickupbackend.REPOSITORIY;

import com.example.clickupbackend.ENTITIY.WorkSpaceLavozimlari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkspaceLAvozimlariRepositoriy extends JpaRepository<WorkSpaceLavozimlari, Long> {

}
