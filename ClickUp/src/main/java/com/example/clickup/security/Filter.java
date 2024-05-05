package com.example.clickup.security;

import com.example.clickup.controller.UsersController;
import com.example.clickup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    TokenGenerate tokenGenerate;
    @Autowired
    UserService usersService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Auth");
        if (auth != null){
            boolean b = tokenGenerate.TokenTekshirish(auth);
            if (b){
                String userName = tokenGenerate.Deshifrlash(auth);
                if (userName != null){
                    UserDetails userDetails = usersService.loadUserByUsername(userName);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                }else {
                    System.out.println("UserName yaratilmadi!!!");
                }
            }else {
                System.out.println("Tokenning muddati tugagan!");
            }
        }else {
            System.out.println("Token ololmadi!");
        }
        filterChain.doFilter(request,response);
    }

}
