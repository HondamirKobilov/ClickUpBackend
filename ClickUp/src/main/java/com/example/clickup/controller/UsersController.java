package com.example.clickup.controller;

import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.LoginDto;
import com.example.clickup.payload.TasdiqlashDto;
import com.example.clickup.payload.UsersDto;
import com.example.clickup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public HttpEntity<?> AddUser(@RequestBody UsersDto usersDto){
        ApiResponse apiResponse=userService.addUsers(usersDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PostMapping("/userslogin")
    public HttpEntity<?> UsersLogin(@RequestBody LoginDto loginDto){
        ApiResponse apiResponse=userService.usersLogin(loginDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/tasdiqlash")
    public HttpEntity<?> Tasdiqlash(@RequestBody TasdiqlashDto tasdiqlashDto){
        ApiResponse apiResponse=userService.Tasdiqlash(tasdiqlashDto);
        return ResponseEntity.status(apiResponse.isHolat()? 200: 409).body(apiResponse.getXabar());
    }
}
