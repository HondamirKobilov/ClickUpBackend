package com.example.clickup.service;

import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.WorkspaceUserDto;
import com.example.clickup.payload.WorkSpaceDto;

public interface WorkSpaceService {
    ApiResponse addWorkSpace(WorkSpaceDto workSpaceDto);

    ApiResponse addeditremove(WorkspaceUserDto workspaceUserDto, Long workspaceId, Long lavozimId);
}
