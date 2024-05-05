package com.example.clickupbackend.SECURITY.Token;

import com.example.clickupbackend.SERVISE.UsersServis;
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
public class Filtr extends OncePerRequestFilter {
    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    UsersServis usersServis;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("users");
        if(auth!=null){
            boolean b=tokenGenerator.TokenTekshirish(auth);
            if(b){
                String username=tokenGenerator.Deshifrlash(auth);
                if(username!=null){
                    UserDetails userDetails =usersServis.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                }
                System.out.println("UserName yaratilmadi!!!");
            }
            System.out.println("Tokenning muddati tugagan!");
        }
        System.out.println("Token ololmadi");
        filterChain.doFilter(request,response);
    }
}
