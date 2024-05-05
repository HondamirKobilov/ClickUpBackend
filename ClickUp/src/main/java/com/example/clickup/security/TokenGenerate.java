package com.example.clickup.security;

import com.example.clickup.entitiy.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerate {
    String key="1234";
    public String TokenOlish(String username){
        long vaqt=36_00_00*100;
        Date yashashVaqti=new Date(System.currentTimeMillis()+vaqt);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(yashashVaqti)
//                .claim("Lavozim",lavozim.getPlatformaLavozimlari())
                .signWith(SignatureAlgorithm.HS512, key)
//                .claim("Lavozim",lavozim.getPlatformaLavozimlari())
                .compact();
        return token;
    }
    public String Deshifrlash(String token){
        String username = Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println(username);
        return username;
    }
    public boolean TokenTekshirish(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
