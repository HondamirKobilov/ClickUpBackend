package com.example.clickupbackend.ENTITIY;

import com.example.clickupbackend.ENTITIY.ABSTRAKT.LongAbstraktEntitiy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class IshchilarSoni extends LongAbstraktEntitiy {

    @Column(nullable = false)
    private String ishchilarSoni;
}
