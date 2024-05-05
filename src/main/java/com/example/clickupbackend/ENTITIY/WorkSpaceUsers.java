package com.example.clickupbackend.ENTITIY;

import com.example.clickupbackend.ENTITIY.ABSTRAKT.UUIDabstraktEntitiy;
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
public class WorkSpaceUsers extends UUIDabstraktEntitiy {
    @ManyToOne
    private Users users;
    @ManyToOne
    private Workspace workspace;
    @ManyToOne
    private WorkSpaceLavozimlari workSpaceLavozimlari;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp taklifQilinganVaqt;
    private Timestamp qabulQilinganVaqt;
}
