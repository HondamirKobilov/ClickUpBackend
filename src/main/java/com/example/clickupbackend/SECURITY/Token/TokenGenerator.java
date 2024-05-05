package com.example.clickupbackend.SECURITY.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
    String kalit="1234";

    public String TokenOlish(String username){
        long vaqt=60*60*100*24;
        Date yashashVaqti=new Date(System.currentTimeMillis()+vaqt);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(yashashVaqti)
                .signWith(SignatureAlgorithm.HS512, kalit)
                .compact();
        return token;

    }
    public boolean TokenTekshirish(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(kalit)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String Deshifrlash(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(kalit)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println(username);
        return username;
    }
}
