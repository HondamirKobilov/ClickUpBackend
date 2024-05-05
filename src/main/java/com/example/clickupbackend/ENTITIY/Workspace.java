package com.example.clickupbackend.ENTITIY;

import com.example.clickupbackend.ENTITIY.ABSTRAKT.LongAbstraktEntitiy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Workspace extends LongAbstraktEntitiy {
    @Column(nullable = false)
    private String nomi;

    @Column(nullable = false)
    private String profilRangi;

    @Column(nullable = false)
    private String workspacePanellarRangi;

    @OneToOne
    private Users users;

    @ManyToOne
    private IshchilarSoni ishchilarSoni;
    @OneToOne
    private Reklama reklama;

    @Transient
    private String boshHarflar;
    public void setBoshHarflar(String nomi) {
        String[] sozlar=nomi.split(" ");
        this.boshHarflar = sozlar[0].substring(0)+sozlar[sozlar.length-1].substring(0);
    }

    public Workspace(String nomi, String profilRangi, String workspacePanellarRangi, Users users, IshchilarSoni ishchilarSoni, Reklama reklama) {
        this.nomi = nomi;
        this.profilRangi = profilRangi;
        this.workspacePanellarRangi = workspacePanellarRangi;
        this.users = users;
        this.ishchilarSoni = ishchilarSoni;
        this.reklama = reklama;
    }
}
