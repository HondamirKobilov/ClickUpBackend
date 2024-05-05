package com.example.clickupbackend.CONTROLLER;
import com.example.clickupbackend.DTO.Apiresponsive;
import com.example.clickupbackend.DTO.WorkspaceDto;
import com.example.clickupbackend.SERVISE.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workspace")
public class WorkSpaceController {
    @Autowired
    WorkspaceService workspaceService;

    @PostMapping("/workspaceAdd")
    public HttpEntity<?> UsersAdd(@RequestBody WorkspaceDto workspaceDto){
        Apiresponsive apiresponsive =workspaceService.workspaceADD(workspaceDto);
        return ResponseEntity.status(apiresponsive.isHolat()?200:208).body(apiresponsive.getXabar());
    }
}
