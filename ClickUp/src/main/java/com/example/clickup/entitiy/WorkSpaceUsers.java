package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.UuidAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkSpaceUsers extends UuidAbstractEntity {
    @ManyToOne
    private Users users;
    @ManyToOne
    private WorkSpace workSpace;
    @ManyToOne
    private WorkSpaceLavozimlari workSpaceLavozimlari;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp taklifQilinganVaqt;
    private Timestamp qabulQilinganVaqt;
}
