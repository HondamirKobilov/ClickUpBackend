package com.example.clickupbackend.REPOSITORIY;

import com.example.clickupbackend.ENTITIY.WorkSpaceUsers;
import com.example.clickupbackend.SERVISE.WorkspaceServiseImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceUsersRepositoriy extends JpaRepository<WorkSpaceUsers, Long> {

}
