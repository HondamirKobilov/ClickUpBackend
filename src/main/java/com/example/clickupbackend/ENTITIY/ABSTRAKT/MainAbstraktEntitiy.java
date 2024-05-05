package com.example.clickupbackend.ENTITIY.ABSTRAKT;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class MainAbstraktEntitiy {
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp yaratilganVaqt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp tahrirlanganVaqt;

    @CreatedBy
    private UUID kimTomonidanYaratilgan;

    @LastModifiedBy
    private UUID kimTomonidanTahrirlangan;

}
