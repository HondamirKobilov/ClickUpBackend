package com.example.clickupbackend.SERVISE;

import com.example.clickupbackend.DTO.Apiresponsive;
import com.example.clickupbackend.DTO.WorkspaceDto;
import com.example.clickupbackend.REPOSITORIY.WorkspaceRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface WorkspaceService {

    Apiresponsive workspaceADD(WorkspaceDto workspaceDto);
}
