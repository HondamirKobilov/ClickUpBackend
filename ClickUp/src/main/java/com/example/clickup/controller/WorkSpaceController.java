package com.example.clickup.controller;

import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.WorkspaceUserDto;
import com.example.clickup.payload.WorkSpaceDto;
import com.example.clickup.service.WorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspace")
public class WorkSpaceController {
    @Autowired
    WorkSpaceService workSpaceService;
    @PostMapping("/addWorkSpace")
    public HttpEntity<?> AddWorkSpace(@RequestBody WorkSpaceDto workSpaceDto){
        ApiResponse apiResponse=workSpaceService.addWorkSpace(workSpaceDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }

    @PostMapping("/AddEditRemove/{workspaceId}/{lavozimId}")
    public HttpEntity<?> AddEditRemove(@RequestBody WorkspaceUserDto workspaceUserDto, @PathVariable Long workspaceId,@PathVariable Long lavozimId){
        ApiResponse apiResponse = workSpaceService.addeditremove(workspaceUserDto,workspaceId,lavozimId);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }

}
