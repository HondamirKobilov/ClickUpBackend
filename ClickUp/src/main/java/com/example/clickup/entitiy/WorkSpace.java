package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nomi", "users_id"})})
public class WorkSpace extends LongAbstractEntity {
    private String nomi;
    private String profilRangi;
    private String ishXonaRangi;
    @OneToOne
    private IshchilarSoni ishchilarSoni;
    @ManyToOne
    private Users users;
    @OneToOne
    private Reklama reklama;

    @Transient
    private String boshHarflar;
    public void setBoshHarflar(String nomi) {
        String[] sozlar=nomi.split(" ");
        this.boshHarflar = sozlar[0].substring(0)+sozlar[sozlar.length-1].substring(0);
    }

    public WorkSpace(String nomi, String profilRangi, String ishXonaRangi, IshchilarSoni ishchilarSoni, Users users, Reklama reklama) {
        this.nomi = nomi;
        this.profilRangi = profilRangi;
        this.ishXonaRangi = ishXonaRangi;
        this.ishchilarSoni = ishchilarSoni;
        this.users = users;
        this.reklama = reklama;
    }
}
