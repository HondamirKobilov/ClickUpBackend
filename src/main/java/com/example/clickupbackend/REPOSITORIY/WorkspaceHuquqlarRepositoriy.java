package com.example.clickupbackend.REPOSITORIY;

import com.example.clickupbackend.ENTITIY.WorkSpaceHuquqlari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceHuquqlarRepositoriy extends JpaRepository<WorkSpaceHuquqlari, Long> {
}
