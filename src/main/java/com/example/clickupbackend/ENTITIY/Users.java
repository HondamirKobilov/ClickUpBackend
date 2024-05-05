package com.example.clickupbackend.ENTITIY;

import com.example.clickupbackend.ENTITIY.ABSTRAKT.UUIDabstraktEntitiy;
import com.example.clickupbackend.ENTITIY.Enums.PlatformaLavozimlari;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users extends UUIDabstraktEntitiy implements UserDetails {

    @Column(nullable = false)
    private String fish;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String emailKod;


    @Enumerated(EnumType.STRING)
    private PlatformaLavozimlari platformaLavozimlari;
    @Transient // jadvalda ustun yaratmaslik uchun ishlatiladi
    private String boshHarflar;

    public void setBoshHarflar(String boshHarflar) {
        String massiv[] = fish.split(" ");
        this.boshHarflar = massiv[0].substring(0)+massiv[massiv.length-1].substring(0);

    }

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(this.platformaLavozimlari.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    public Users(String fish, String username, String password, PlatformaLavozimlari platformaLavozimlari) {
        this.fish = fish;
        this.username = username;
        this.password = password;
        this.platformaLavozimlari = platformaLavozimlari;
    }
}
