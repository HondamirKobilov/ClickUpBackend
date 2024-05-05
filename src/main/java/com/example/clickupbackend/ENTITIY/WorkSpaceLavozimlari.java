package com.example.clickupbackend.ENTITIY;
import com.example.clickupbackend.ENTITIY.ABSTRAKT.LongAbstraktEntitiy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkSpaceLavozimlari extends LongAbstraktEntitiy {
    @ManyToOne
    private Workspace workSpace;
    @Column(nullable = false)
    private String lavozmiNomlari;
}
