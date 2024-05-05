package com.example.clickupbackend.CONTROLLER;

import com.example.clickupbackend.DTO.Apiresponsive;
import com.example.clickupbackend.DTO.LoginDto;
import com.example.clickupbackend.DTO.TasdiqlashDto;
import com.example.clickupbackend.DTO.UsersDto;
import com.example.clickupbackend.SERVISE.UsersServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usersAPI")
public class UsersController {
    @Autowired
    UsersServis usersServis;

    @PostMapping("/usersAdd")
    public HttpEntity<?> UsersAdd(@RequestBody UsersDto usersDto){
        Apiresponsive apiresponsive =usersServis.userAdd(usersDto);
        return ResponseEntity.status(apiresponsive.isHolat()?200:208).body(apiresponsive.getXabar());
    }

    @GetMapping("/tasdiqlash")
    public HttpEntity<?> Tasdiqlash(@RequestBody TasdiqlashDto tasdiqlashDto){
        Apiresponsive apiresponsive = usersServis.faollashtirish(tasdiqlashDto);
        return ResponseEntity.status(apiresponsive.isHolat()? 200: 409).body(apiresponsive.getXabar());
    }
    @PostMapping("/xodimlogin")
    public HttpEntity<?> XodimLogin(@Valid @RequestBody LoginDto loginDto){
        Apiresponsive apiresponsive = usersServis.UserLogin(loginDto);
        return ResponseEntity.status(apiresponsive.isHolat()?200:208).body(apiresponsive.getXabar());
    }
}
