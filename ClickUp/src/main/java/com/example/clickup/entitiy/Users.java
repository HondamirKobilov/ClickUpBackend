package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.UuidAbstractEntity;
import com.example.clickup.entitiy.enums.PlatformaLavozimlari;
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
public class Users extends UuidAbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String fish;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String kod;
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;
    @Enumerated(EnumType.STRING)
    private PlatformaLavozimlari platformaLavozimlari;
    @Transient
    private String boshHarflar;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(this.platformaLavozimlari.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    public void setBoshHarflar(String fish) {
        String[] sozlar=fish.split(" ");
        this.boshHarflar = sozlar[0].substring(0)+sozlar[sozlar.length-1].substring(0);
    }

    public Users(String fish, String username, String password, String kod, PlatformaLavozimlari platformaLavozimlari) {
        this.fish = fish;
        this.username = username;
        this.password = password;
        this.kod = kod;
        this.platformaLavozimlari = platformaLavozimlari;
    }
}
